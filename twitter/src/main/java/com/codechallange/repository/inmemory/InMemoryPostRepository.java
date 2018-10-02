package com.codechallange.repository.inmemory;

import com.codechallange.entity.PostEntity;
import com.codechallange.repository.PostRepository;

import javax.inject.Singleton;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Singleton
public class InMemoryPostRepository implements PostRepository {

    private Map<String, PostEntity> map = new ConcurrentHashMap<>();

    public PostEntity createPost(String username, String message) {
        requireNonNull(message);
        if (message.length() > 140) {
            throw new IllegalArgumentException("Message is too long!");
        }
        PostEntity post = new PostEntity();
        post.setUsername(username);
        post.setMessage(message);
        post.setDate(Date.from(Instant.now()));
        post.setKey();
        map.put(post.getKey(), post);
        return post;
    }

    public List<PostEntity> getPostsOfUser(String username) {
        return map.values()
                .stream()
                .filter(post -> post.getUsername().equals(username))
                .sorted(Comparator.comparing(PostEntity::getDate).reversed())
                .collect(Collectors.toList());
    }

    public List<PostEntity> getPostsOfUsers(Set<String> usernames) {
        return map.values()
                .stream()
                .filter(post -> usernames.contains(post.getUsername()))
                .sorted(Comparator.comparing(PostEntity::getDate).reversed())
                .collect(Collectors.toList());
    }

    public void clear() {
        map.clear();
    }
}
