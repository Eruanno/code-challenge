package com.codechallange.boundary;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private String username;
    private Set<String> followingUsers;

    private UserDTO(Builder builder) {
        this.username = builder.username;
        this.followingUsers = builder.followingUsers;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getFollowingUsers() {
        return new HashSet<>(followingUsers);
    }

    public static class Builder {
        private String username;
        private Set<String> followingUsers = new HashSet<>();

        public Builder() {
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setFollowingUsers(Set<String> followingUsers) {
            this.followingUsers = new HashSet<>(followingUsers);
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }
}
