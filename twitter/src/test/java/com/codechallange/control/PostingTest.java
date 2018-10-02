package com.codechallange.control;

import com.codechallange.boundary.PostDTO;
import com.codechallange.repository.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PostingTest extends AbstractTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        Repository.cleanRepositories();
        TOO_LONG_MESSAGE = IntStream.range(0, 15).mapToObj(o -> TEN_DIGITS_STRING).collect(Collectors.joining());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException() {
        postService.createPostForUser(USER_1, null);
    }

    @Test
    public void shouldAcceptNormalMessage() {
        PostDTO post = postService.createPostForUser(USER_1, TEN_DIGITS_STRING);
        Assert.assertNotNull(post);
        Assert.assertEquals(TEN_DIGITS_STRING, post.getMessage());
        Assert.assertNotNull(post.getDate());
        Assert.assertEquals(USERNAME_1, post.getUsername());
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Message is too long!");
        postService.createPostForUser(USER_1, TOO_LONG_MESSAGE);
    }
}