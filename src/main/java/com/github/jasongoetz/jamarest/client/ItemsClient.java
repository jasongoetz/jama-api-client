package com.github.jasongoetz.jamarest.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jasongoetz.jamarest.domain.wrapper.PageResults;
import com.github.jasongoetz.jamarest.domain.item.Item;
import com.github.jasongoetz.jamarest.domain.item.ItemWrapper;
import com.github.jasongoetz.jamarest.domain.item.ItemsWrapper;
import com.github.jasongoetz.jamarest.domain.item.RequestItem;
import com.github.jasongoetz.jamarest.exception.ApiException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class ItemsClient extends AbstractResourceClient {

    public ItemsClient(String jamaURL, String username, String password) {
        super(jamaURL, username, password);
    }

    public PageResults<Item> getItems(Integer projectId, Integer startAt, Integer maxResults) {
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        params.add("project", projectId);
        if (startAt != null) {
            params.add("startAt", startAt);
        }
        if (maxResults != null) {
            params.add("maxResults", maxResults);
        }
        ItemsWrapper itemsWrapper = get("items", params).getEntity(ItemsWrapper.class);
        return new PageResults(itemsWrapper.getData(), itemsWrapper.getMeta().getPageInfo());
    }

    public Item getItem(Integer itemId) {
        return get("items/"+itemId).getEntity(ItemWrapper.class).getData();
    }

    public Integer createItem(RequestItem item) {
        try {
            String itemJson = ClientUtil.getJsonStringFromObject(item);
            ClientResponse response = post("items", itemJson);
            return ClientUtil.getIdFromPostResponse(response);
        } catch (JsonProcessingException e) {
            throw new ApiException(ClientResponse.Status.BAD_REQUEST, e.getMessage());
        }
    }

    public void updateItem(Integer itemId, RequestItem item) {
        try {
            String itemJson = ClientUtil.getJsonStringFromObject(item);
            put("items/" + itemId, itemJson);
        } catch (JsonProcessingException e) {
            throw new ApiException(ClientResponse.Status.BAD_REQUEST, e.getMessage());
        }
    }



}
