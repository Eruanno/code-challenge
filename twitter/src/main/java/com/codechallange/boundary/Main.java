package com.codechallange.boundary;

import com.codechallange.control.PostService;
import com.codechallange.control.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/")
public class Main {

    @Inject
    PostService postService;

    @Inject
    UserService userService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("post/{username}")
    public Response createPost(@PathParam("username") String username, @QueryParam("message") String message) {
        UserDTO user = new UserDTO.Builder().setUsername(username).build();
        postService.createPostForUser(user,  message);
        return Response.created(URI.create("")).build();
    }

    @GET
    @Produces("application/json")
    @Path("wall/{username}")
    public WallDTO getWall(@PathParam("username") String username, @QueryParam("start") @DefaultValue("0") Integer start, @QueryParam("length") @DefaultValue("10") Integer length) {
        UserDTO user = new UserDTO.Builder().setUsername(username).build();
        return postService.getUserWall(user, start, length);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{user}/follow/{username}")
    public UserDTO followUser(@PathParam("user") String user, @PathParam("username") String username) {
        UserDTO u = new UserDTO.Builder().setUsername(user).build();
        return userService.addFollowingUser(u, username);
    }

    @GET
    @Produces("application/json")
    @Path("timeline/{username}")
    public TimelineDTO getTimeline(@PathParam("username") String username, @QueryParam("start") @DefaultValue("0") Integer start, @QueryParam("length") @DefaultValue("10") Integer length) {
        UserDTO user = new UserDTO.Builder().setUsername(username).build();
        return postService.getUserTimeline(user, start, length);
    }
}
