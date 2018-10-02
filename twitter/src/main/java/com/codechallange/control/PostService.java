package com.codechallange.control;

import com.codechallange.boundary.PostDTO;
import com.codechallange.boundary.TimelineDTO;
import com.codechallange.boundary.WallDTO;
import com.codechallange.entity.PostEntity;
import com.codechallange.entity.UserEntity;
import com.codechallange.repository.dao.PostDAO;
import com.codechallange.repository.dao.UserDAO;
import com.codechallange.boundary.UserDTO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PostService {

    private static Function<PostEntity, PostDTO> PostEntityToDTOFunction = (post) -> new PostDTO.Builder().setUsername(post.getUsername())
            .setMessage(post.getMessage())
            .setDate(post.getDate())
            .setKey(post.getKey())
            .build();

    public PostDTO createPostForUser(UserDTO user, String message) {
        UserEntity userEntity = UserDAO.findUserByUsername(user.getUsername());
        PostEntity post = PostDAO.createPostForUser(userEntity.getUsername(), message);
        return PostEntityToDTOFunction.apply(post);
    }

    public WallDTO getUserWall(UserDTO user, Integer start, Integer length) {
        List<PostEntity> posts = PostDAO.getUserPosts(user.getUsername(), start, length);
        List<PostDTO> dtos = posts.stream()
                .map(PostEntityToDTOFunction)
                .collect(Collectors.toList());
        return new WallDTO.Builder().setPosts(dtos).build();
    }

    public TimelineDTO getUserTimeline(UserDTO user, Integer start, Integer length) {
        UserEntity userEntity = UserDAO.findUserByUsername(user.getUsername());
        List<PostEntity> posts = PostDAO.getPostsOfUserFollowings(userEntity.getFollowingUsers(), start, length);
        List<PostDTO> dtos = posts.stream()
                .map(PostEntityToDTOFunction)
                .collect(Collectors.toList());
        return new TimelineDTO.Builder().setPosts(dtos).build();
    }
}
