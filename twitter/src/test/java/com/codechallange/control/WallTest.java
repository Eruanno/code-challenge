package com.codechallange.control;

import com.codechallange.boundary.PostDTO;
import com.codechallange.repository.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class WallTest extends AbstractTest {

    @Before
    public void setup() {
        Repository.cleanRepositories();
    }

    @Test
    public void shouldReturnWallWithTwoMessagesInReverseChronoogicalOrder() throws InterruptedException {
        postService.createPostForUser(USER_1, TEN_DIGITS_STRING);
        Thread.sleep(1000);
        postService.createPostForUser(USER_1, TEN_DIGITS_STRING);
        List<PostDTO> posts = postService.getUserWall(USER_1, 0, 10).getPosts();
        Assert.assertEquals(2, posts.size());
        Assert.assertTrue(posts.get(0).getDate().compareTo(posts.get(1).getDate()) > 0);
    }
}
