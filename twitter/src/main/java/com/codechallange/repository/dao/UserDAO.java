package com.codechallange.repository.dao;

import com.codechallange.entity.UserEntity;
import com.codechallange.repository.Repository;

public class UserDAO {

    public static UserEntity addFollowingUser(String user, String username) {
        UserEntity repoUser = Repository.getUserRepository().getUser(user);
        repoUser.getFollowingUsers().add(username);
        Repository.getUserRepository().updateUser(repoUser);
        return repoUser;
    }

    public static UserEntity findUserByUsername(String username) {
        return Repository.getUserRepository().getUser(username);
    }
}
