package com.codechallange.repository.dao;

import com.codechallange.entity.UserEntity;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Singleton;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

@Startup
@Singleton
public class UserDAO implements Dao<UserEntity> {

    private Map<String, UserEntity> map = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {}

    @Override
    public Optional<UserEntity> get(String id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void create(UserEntity user) {
        requireNonNull(user);
        map.put(user.getUsername(), user);
    }

    @Override
    public void update(UserEntity user) {
        requireNonNull(user);
        map.put(user.getUsername(), user);
    }
}
