package com.codechallange.boundary;

import java.util.List;

public class WallDTO {

    private List<PostDTO> posts;

    private WallDTO(Builder builder) {
        this.posts = builder.posts;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public static class Builder {
        private List<PostDTO> posts;

        public Builder() {
        }

        public Builder setPosts(List<PostDTO> posts) {
            this.posts = posts;
            return this;
        }

        public WallDTO build() {
            return new WallDTO(this);
        }
    }
}