package com.github.jasongoetz.jamarest.client;

import com.github.jasongoetz.jamarest.domain.wrapper.ErrorWrapper;
import com.github.jasongoetz.jamarest.exception.ApiException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

public abstract class AbstractResourceClient {

    private final String JAMA_VERSION = "v1";

    protected WebResource service;

    public AbstractResourceClient(String jamaURL, String username, String password) {
        ClientConfig config = new DefaultClientConfig();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JacksonJsonProvider provider = new JacksonJsonProvider(mapper);
        config.getSingletons().add(provider);
        Client client = Client.create(config);
        client.addFilter(new HTTPBasicAuthFilter(username, password));
        service = client.resource(UriBuilder.fromUri(getRestApiURL(jamaURL)).build());
    }

    protected ClientResponse get(String url){
        return get(url, null);
    }

    protected ClientResponse get(String url, MultivaluedMapImpl queryParams){
        return request("GET", url, queryParams);
    }

    protected ClientResponse post(String url, String data){
        return request("POST", url, null, data);
    }

    protected ClientResponse put(String url, String data){
        return request("PUT", url, null, data);
    }

    protected ClientResponse delete(String url){
        return request("DELETE", url);
    }

    protected ClientResponse request(String method, String url){
        return request(method, url, null);
    }

    protected ClientResponse request(String method, String url, MultivaluedMapImpl queryParams) {
        return request(method, url, queryParams, null);
    }

    protected ClientResponse request(String method, String url, MultivaluedMapImpl queryParams, String data) {
        ClientResponse clientResponse = getResourceBuilder(url, queryParams).type("application/json").method(method, ClientResponse.class, data);
        checkForErrors(clientResponse);
        return clientResponse;
    }

    private void checkForErrors(ClientResponse clientResponse){
        if(clientResponse.getClientResponseStatus().getStatusCode() != ClientResponse.Status.OK.getStatusCode()
                && clientResponse.getClientResponseStatus().getStatusCode() != ClientResponse.Status.CREATED.getStatusCode()
                && clientResponse.getClientResponseStatus().getStatusCode() != ClientResponse.Status.NO_CONTENT.getStatusCode()) {
            throw new ApiException(clientResponse.getClientResponseStatus(), clientResponse.getEntity(ErrorWrapper.class).getMeta().getMessage());
        }
    }

    private WebResource.Builder getResourceBuilder(String url, MultivaluedMapImpl queryParams) {
        WebResource webResource = service.path(url);
        if(queryParams != null){
            webResource = webResource.queryParams(queryParams);
        }
        return webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_FORM_URLENCODED);
    }

    private String getRestApiURL(String jamaURL) {
        return jamaURL + "/rest/" + JAMA_VERSION + "/";
    }

}
