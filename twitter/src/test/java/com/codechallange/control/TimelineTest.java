package com.codechallange.control;

import com.codechallange.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimelineTest extends AbstractTest {

    @Test
    public void shouldAddFollowingUserWithoutDuplicates() {
        UserEntity user = UserEntity.createNewUserEntity(USERNAME_1);
        assertEquals(0, user.getFollowingUsers().size());
        user.getFollowingUsers().add(USERNAME_2);
        userDAO.update(user);
        assertEquals(1, userDAO.get(USERNAME_1).get().getFollowingUsers().size());
        user.getFollowingUsers().add(USERNAME_2);
        userDAO.update(user);
        assertEquals(1, userDAO.get(USERNAME_1).get().getFollowingUsers().size());
    }
}