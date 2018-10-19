package com.codechallange.control;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.stream.IntStream;

public class SublistTest extends AbstractTest {

    @Before
    public void setup() {
        IntStream.range(0, 15).forEach(i -> postDAO.create(createPost(USERNAME_1, TEN_DIGITS_STRING, Date.from(Instant.now().minusMillis(1000*i)))));
    }

    @Test
    public void shouldReturnRightAmountOfPosts() {
        Assert.assertEquals(10, postService.getUserWall(USER_1, 0, 10).getPosts().size());
        Assert.assertEquals(5, postService.getUserWall(USER_1, 10, 10).getPosts().size());
        Assert.assertEquals(0, postService.getUserWall(USER_1, 20, 10).getPosts().size());
    }
}
