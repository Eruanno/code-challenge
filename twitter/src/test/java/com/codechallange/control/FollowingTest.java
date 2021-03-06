package com.codechallange.control;

import com.codechallange.boundary.dto.PostDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FollowingTest extends AbstractTest {

    @Test
    public void shouldReturnTimelineWithTwoMessagesInReverseChronologicalOrder() {
        userService.createUser(USER_1);
        userService.createUser(USER_2);
        userService.createUser(USER_3);
        postService.createPostForUser(USER_2, TEN_DIGITS_STRING);
        postService.createPostForUser(USER_3, TEN_DIGITS_STRING);
        userService.addFollowingUser(USER_1, USERNAME_2);
        userService.addFollowingUser(USER_1, USERNAME_3);
        List<PostDTO> posts = postService.getUserTimeline(USER_1, 0, 10).getPosts();
        Assert.assertEquals(2, posts.size());
        Assert.assertEquals(USERNAME_3, posts.get(0).getUsername());
        Assert.assertEquals(USERNAME_2,posts.get(1).getUsername());
        Assert.assertTrue(posts.get(0).getDate().compareTo(posts.get(1).getDate()) > 0);
    }
}
