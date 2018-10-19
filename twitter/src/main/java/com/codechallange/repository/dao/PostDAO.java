package com.codechallange.repository.dao;

import com.codechallange.entity.PostEntity;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Singleton;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Startup
@Singleton
public class PostDAO implements Dao<PostEntity> {

    private Map<String, PostEntity> map = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {}

    @Override
    public Optional<PostEntity> get(String id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void create(PostEntity post) {
        requireNonNull(post);
        map.put(post.getKey(), post);
    }

    @Override
    public void update(PostEntity post) {
        requireNonNull(post);
        map.put(post.getKey(), post);
    }

    public List<PostEntity> getUserPosts(String username, Integer start, Integer length) {
        List<PostEntity> posts = map.values()
                .stream()
                .filter(post -> post.getUsername().equals(username))
                .sorted(Comparator.comparing(PostEntity::getDate).reversed())
                .collect(Collectors.toList());
        return sublist(posts, start, length);
    }

    public List<PostEntity> getUsersPosts(Set<String> usernames, Integer start, Integer length) {
        List<PostEntity> posts = map.values()
                .stream()
                .filter(post -> usernames.contains(post.getUsername()))
                .sorted(Comparator.comparing(PostEntity::getDate).reversed())
                .collect(Collectors.toList());
        return sublist(posts, start, length);
    }

    private static List<PostEntity> sublist(List<PostEntity> posts, Integer start, Integer length) {
        if (posts.size() > start + length) {
            return posts.subList(start, start + length);
        }
        if (posts.size() > start) {
            return posts.subList(start, posts.size());
        }
        if (posts.size() < start) {
            return Collections.emptyList();
        }
        return posts;
    }
}
