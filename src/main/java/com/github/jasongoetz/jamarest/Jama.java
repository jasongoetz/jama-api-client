package com.github.jasongoetz.jamarest;

import com.github.jasongoetz.jamarest.client.AbstractResourceClient;
import com.github.jasongoetz.jamarest.client.ItemsClient;
import com.github.jasongoetz.jamarest.client.UsersClient;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Jama {

    private String jamaURL;

    private String username;

    private String password;

    protected Map<Class<? extends AbstractResourceClient>, AbstractResourceClient> clients;

    public Jama(String jamaURL, String username, String password) {
        this.jamaURL = jamaURL;
        this.username = username;
        this.password = password;
        clients = new HashMap();
    }

    @SuppressWarnings("unchecked")
    protected <T> T getClient(Class<? extends AbstractResourceClient> clazz){
        if(!clients.containsKey(clazz)){
            try {
                clients.put(clazz, clazz.getConstructor(String.class, String.class, String.class).newInstance(jamaURL, username, password));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return (T) clients.get(clazz);
    }

    public UsersClient users() {
        return getClient(UsersClient.class);
    }

    public ItemsClient items() {
        return getClient(ItemsClient.class);
    }

}
