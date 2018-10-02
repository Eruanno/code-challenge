package com.codechallange.control;

import com.codechallange.entity.UserEntity;
import com.codechallange.repository.dao.UserDAO;
import com.codechallange.boundary.UserDTO;

import java.util.function.Function;

public class UserService {

    private static Function<UserEntity, UserDTO> UserEntityToDTOFunction = (user) -> new UserDTO.Builder()
            .setUsername(user.getUsername())
            .setFollowingUsers(user.getFollowingUsers())
            .build();

    public UserDTO addFollowingUser(UserDTO user, String username) {
        UserEntity userEntity = UserDAO.addFollowingUser(user.getUsername(), username);
        return UserEntityToDTOFunction.apply(userEntity);
    }
}
