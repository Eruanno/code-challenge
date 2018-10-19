package com.codechallange.control;

import com.codechallange.entity.UserEntity;
import com.codechallange.repository.dao.PostDAO;
import com.codechallange.repository.dao.UserDAO;
import com.codechallange.boundary.dto.UserDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;
import java.util.function.Function;

public class UserService {

    @Inject
    private UserDAO userDAO;

    public UserService() {}

    UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private static Function<UserEntity, UserDTO> UserEntityToDTOFunction = (user) -> new UserDTO.Builder()
            .setUsername(user.getUsername())
            .setFollowingUsers(user.getFollowingUsers())
            .build();

    void createUser(UserDTO userDTO) {
        UserEntity user = UserEntity.createNewUserEntity(userDTO.getUsername());
        userDAO.create(user);
    }

    public UserDTO addFollowingUser(UserDTO userDTO, String username) {
        Optional<UserEntity> userEntity = userDAO.get(userDTO.getUsername());
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            user.getFollowingUsers().add(username);
            userDAO.update(user);
            return UserEntityToDTOFunction.apply(user);
        }
        throw new IllegalArgumentException("User does not exist");
    }
}
