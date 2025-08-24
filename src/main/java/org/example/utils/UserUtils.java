package org.example.utils;

import org.example.model.User;

public class UserUtils {
    public static String print(User user) {
        return user == null ? "NO USER" : user.toString();
    }
}
