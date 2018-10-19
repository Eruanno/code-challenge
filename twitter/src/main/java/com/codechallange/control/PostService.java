package com.codechallange.control;

import com.codechallange.boundary.dto.PostDTO;
import com.codechallange.boundary.dto.TimelineDTO;
import com.codechallange.boundary.dto.WallDTO;
import com.codechallange.entity.PostEntity;
import com.codechallange.entity.UserEntity;
import com.codechallange.repository.dao.PostDAO;
import com.codechallange.repository.dao.UserDAO;
import com.codechallange.boundary.dto.UserDTO;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class PostService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private PostDAO postDAO;

    public PostService() {}

    PostService(UserDAO userDAO, PostDAO postDAO) {
        this.userDAO = userDAO;
        this.postDAO = postDAO;
    }

    private static Function<PostEntity, PostDTO> PostEntityToDTOFunction = (post) -> new PostDTO.Builder().setUsername(post.getUsername())
            .setMessage(post.getMessage())
            .setDate(post.getDate())
            .setKey(post.getKey())
            .build();

    public PostDTO createPostForUser(UserDTO user, String message) {
        UserEntity userEntity = getUserFromRepository(user.getUsername());
        PostEntity post = createPost(userEntity, message);
        postDAO.create(post);
        return PostEntityToDTOFunction.apply(post);
    }

    public WallDTO getUserWall(UserDTO user, Integer start, Integer length) {
        List<PostEntity> posts = postDAO.getUserPosts(user.getUsername(), start, length);
        List<PostDTO> dtos = posts.stream()
                .map(PostEntityToDTOFunction)
                .collect(Collectors.toList());
        return new WallDTO.Builder().setPosts(dtos).build();
    }

    public TimelineDTO getUserTimeline(UserDTO user, Integer start, Integer length) {
        UserEntity userEntity = getUserFromRepository(user.getUsername());
        List<PostEntity> posts = postDAO.getUsersPosts(userEntity.getFollowingUsers(), start, length);
        List<PostDTO> dtos = posts.stream()
                .map(PostEntityToDTOFunction)
                .collect(Collectors.toList());
        return new TimelineDTO.Builder().setPosts(dtos).build();
    }

    private UserEntity getUserFromRepository(String username) {
        Optional<UserEntity> userEntity = userDAO.get(username);
        return userEntity.orElseGet(() -> createNewUser(username));
    }

    private UserEntity createNewUser(String username) {
        UserEntity user = UserEntity.createNewUserEntity(username);
        userDAO.create(user);
        return user;
    }

    private PostEntity createPost(UserEntity user, String message) {
        requireNonNull(message);
        if (message.length() > 140) {
            throw new IllegalArgumentException("Message is too long!");
        }
        PostEntity post = new PostEntity();
        post.setUsername(user.getUsername());
        post.setMessage(message);
        post.setDate(Date.from(Instant.now()));
        post.setKey();
        return post;
    }
}
