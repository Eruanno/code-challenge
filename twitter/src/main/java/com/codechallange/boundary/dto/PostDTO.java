package com.codechallange.boundary.dto;

import java.util.Date;

public class PostDTO {

    private String key;
    private String username;
    private String message;
    private Date date;

    private PostDTO(Builder builder) {
        this.username = builder.username;
        this.message = builder.message;
        this.date = builder.date;
        this.key = builder.key;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public String getKey() {
        return key;
    }

    public static class Builder {
        private String key;
        private String username;
        private String message;
        private Date date;

        public Builder() {
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public PostDTO build() {
            return new PostDTO(this);
        }
    }
}
