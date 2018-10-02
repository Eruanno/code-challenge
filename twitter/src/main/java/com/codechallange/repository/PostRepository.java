package com.codechallange.repository;

import com.codechallange.entity.PostEntity;

import java.util.List;
import java.util.Set;

public interface PostRepository {
    PostEntity createPost(String username, String message);
    List<PostEntity> getPostsOfUser(String username);
    List<PostEntity> getPostsOfUsers(Set<String> usernames);
    void clear();
}
