package com.codechallange.control;

import com.codechallange.boundary.dto.PostDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class WallTest extends AbstractTest {

    @Test
    public void shouldReturnWallWithTwoMessagesInReverseChronoogicalOrder() {
        postDAO.create(createPost(USERNAME_1, TEN_DIGITS_STRING, Date.from(Instant.now())));
        postDAO.create(createPost(USERNAME_1, TEN_DIGITS_STRING, Date.from(Instant.now().minusMillis(1000L))));
        List<PostDTO> posts = postService.getUserWall(USER_1, 0, 10).getPosts();
        Assert.assertEquals(2, posts.size());
        Assert.assertTrue(posts.get(0).getDate().compareTo(posts.get(1).getDate()) > 0);
    }
}
