package com.github.jasongoetz.jamarest.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

public class ClientUtil {

    public static String getJsonStringFromObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static Integer getIdFromPostResponse(ClientResponse response) {
        String locationUrlFromResponse = getLocationUrlFromResponse(response);
        String idFromPostLocationUrl = getIdFromPostLocationUrl(locationUrlFromResponse);
        return Integer.parseInt(idFromPostLocationUrl);
    }

    private static String getIdFromPostLocationUrl(String locationUrl) {
        return locationUrl.substring(locationUrl.lastIndexOf("/") + 1, locationUrl.length());
    }

    private static String getLocationUrlFromResponse(ClientResponse response) {
        return response.getHeaders().getFirst("Location");
    }

}
