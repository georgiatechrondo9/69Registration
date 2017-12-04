package com.apatel428.a69registration.helpers;

import com.apatel428.a69registration.model.User;

/**
 * Created by 14bpo on 12/2/2017.
 */

public class ActiveUserHolder {
    private static User user;

    ActiveUserHolder(){}

    public static void setUser(User u) {
        user = u;
    }

    public static User getUser() {
        return user;
    }
}
