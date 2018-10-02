package com.codechallange.boundary;

import java.util.List;

public class TimelineDTO {
    private List<PostDTO> posts;

    private TimelineDTO(Builder builder) {
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

        public TimelineDTO build() {
            return new TimelineDTO(this);
        }
    }
}
