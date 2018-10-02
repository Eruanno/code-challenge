package com.codechallange.entity;

import java.util.Date;
import java.util.StringJoiner;

public class PostEntity {

    private String key;
    private String username;
    private String message;
    private Date date;

    public PostEntity() {
    }

    public String getKey() {
        return key;
    }

    public void setKey() {
        this.key = new StringJoiner("_").add(username).add(Long.toString(date.getTime())).toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
