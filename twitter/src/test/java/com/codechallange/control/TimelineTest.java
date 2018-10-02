package com.codechallange.control;

import com.codechallange.entity.UserEntity;
import com.codechallange.repository.Repository;
import com.codechallange.repository.dao.UserDAO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimelineTest extends AbstractTest{

    @Before
    public void setup() {
        Repository.cleanRepositories();
    }

    @Test
    public void shouldAddFollowingUserWithoutDuplicates() {
        UserEntity user1 = UserDAO.findUserByUsername(USERNAME_1);
        assertEquals(0, user1.getFollowingUsers().size());
        UserDAO.addFollowingUser(USERNAME_1, USERNAME_2);
        assertEquals(1, user1.getFollowingUsers().size());
        UserDAO.addFollowingUser(USERNAME_1, USERNAME_2);
        assertEquals(1, user1.getFollowingUsers().size());
    }
}