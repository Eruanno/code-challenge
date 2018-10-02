package com.codechallange.repository.dao;

import com.codechallange.entity.PostEntity;
import com.codechallange.repository.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PostDAO {

    public static PostEntity createPostForUser(String username, String message) {
        return Repository.getPostRepository().createPost(username, message);
    }

    public static List<PostEntity> getUserPosts(String username, Integer start, Integer length) {
        List<PostEntity> posts = Repository.getPostRepository().getPostsOfUser(username);
        return sublist(posts, start, length);
    }

    public static List<PostEntity> getPostsOfUserFollowings(Set<String> usernames, Integer start, Integer length) {
        List<PostEntity> posts = Repository.getPostRepository().getPostsOfUsers(usernames);
        return sublist(posts, start, length);
    }

    private static List<PostEntity> sublist(List<PostEntity> posts, Integer start, Integer length) {
        if(posts.size() > start + length) {
            return posts.subList(start, start + length);
        }
        if(posts.size() > start) {
            return posts.subList(start, posts.size());
        }
        if(posts.size() < start) {
            return Collections.emptyList();
        }
        return posts;
    }
}
