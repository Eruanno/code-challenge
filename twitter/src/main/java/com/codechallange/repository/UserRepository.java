package com.codechallange.repository;

import com.codechallange.entity.UserEntity;

public interface UserRepository {
    UserEntity getUser(String username);
    UserEntity updateUser(UserEntity user);
    void clear();
}
