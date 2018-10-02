package com.codechallange.control;

import com.codechallange.repository.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

public class SublistTest extends AbstractTest {

    @Before
    public void setup() {
        Repository.cleanRepositories();
        IntStream.range(0, 15).forEach(i -> {
            postService.createPostForUser(USER_1, Integer.toString(i));
            sleep();
        });
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnRightAmountOfPosts() {
        Assert.assertEquals(10, postService.getUserWall(USER_1, 0, 10).getPosts().size());
        Assert.assertEquals(5, postService.getUserWall(USER_1, 10, 10).getPosts().size());
        Assert.assertEquals(0, postService.getUserWall(USER_1, 20, 10).getPosts().size());
    }
}
