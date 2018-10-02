package com.codechallange.repository.inmemory;

import com.codechallange.entity.UserEntity;
import com.codechallange.repository.UserRepository;

import javax.inject.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

@Singleton
public class InMemoryUserRepository implements UserRepository {

    private Map<String, UserEntity> map = new ConcurrentHashMap<>();

    public UserEntity getUser(String username) {
        UserEntity user = map.get(username);
        if (user == null) {
            user = new UserEntity();
            user.setUsername(username);
            map.put(username, user);
        }
        return user;
    }

    public UserEntity updateUser(UserEntity user) {
        requireNonNull(user);
        map.put(user.getUsername(), user);
        return user;
    }

    public void clear() {
        map.clear();
    }
}
