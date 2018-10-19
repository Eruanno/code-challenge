package com.codechallange.control;

import com.codechallange.boundary.dto.UserDTO;
import com.codechallange.entity.PostEntity;
import com.codechallange.repository.dao.PostDAO;
import com.codechallange.repository.dao.UserDAO;

import java.util.Date;

abstract class AbstractTest {
    String USERNAME_1 = "test_user_1";

    String USERNAME_2 = "test_user_2";

    String USERNAME_3 = "test_user_3";

    UserDTO USER_1 = new UserDTO.Builder().setUsername(USERNAME_1).build();

    UserDTO USER_2 = new UserDTO.Builder().setUsername(USERNAME_2).build();

    UserDTO USER_3 = new UserDTO.Builder().setUsername(USERNAME_3).build();

    String TOO_LONG_MESSAGE;

    String TEN_DIGITS_STRING = "0123456789";

    UserDAO userDAO = new UserDAO();

    PostDAO postDAO = new PostDAO();

    PostService postService = new PostService(userDAO, postDAO);

    UserService userService = new UserService(userDAO);

    PostEntity createPost(String username, String message, Date date) {
        PostEntity post = new PostEntity();
        post.setUsername(username);
        post.setMessage(message);
        post.setDate(date);
        post.setKey();
        return post;
    }
}
