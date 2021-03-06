package com.github.jasongoetz.jamarest.client;

import com.github.jasongoetz.jamarest.domain.user.User;
import com.github.jasongoetz.jamarest.domain.user.UserWrapper;
import com.github.jasongoetz.jamarest.domain.user.UsersWrapper;
import com.github.jasongoetz.jamarest.domain.wrapper.PageResults;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UsersClient extends AbstractResourceClient {

    public UsersClient(String jamaURL, String username, String password) {
        super(jamaURL, username, password);
    }

    public PageResults<User> getUsers(String username, String email, String firstName, String lastName, Integer startAt, Integer maxResults) {
        MultivaluedMapImpl params = new MultivaluedMapImpl();
        if (username != null) {
            params.add("username", username);
        }
        if (email != null) {
            params.add("email", email);
        }
        if (firstName != null) {
            params.add("firstName", firstName);
        }
        if (lastName != null) {
            params.add("lastName", lastName);
        }
        if (startAt != null) {
            params.add("startAt", startAt);
        }
        if (maxResults != null) {
            params.add("maxResults", maxResults);
        }
        UsersWrapper usersWrapper = get("users", params).getEntity(UsersWrapper.class);
        return new PageResults(usersWrapper.getData(), usersWrapper.getMeta().getPageInfo());
    }

    public User getUser(Integer userId) {
        return get("users/"+userId).getEntity(UserWrapper.class).getData();
    }

}
