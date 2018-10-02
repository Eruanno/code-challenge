package com.codechallange.repository;

import com.codechallange.repository.inmemory.InMemoryPostRepository;
import com.codechallange.repository.inmemory.InMemoryUserRepository;

public class Repository {

    private static final UserRepository userRepository = new InMemoryUserRepository();

    private static final PostRepository postRepository = new InMemoryPostRepository();

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static PostRepository getPostRepository() {
        return postRepository;
    }

    public static void cleanRepositories() {
        userRepository.clear();
        postRepository.clear();
    }
}
