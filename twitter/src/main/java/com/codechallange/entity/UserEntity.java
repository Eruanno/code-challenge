package com.codechallange.entity;

import java.util.HashSet;
import java.util.Set;

public class UserEntity {

    private String username;
    private Set<String> followingUsers = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getFollowingUsers() {
        return followingUsers;
    }
}
