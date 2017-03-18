package com.zhaokxkx13.service;

import com.zhaokxkx13.StatApplication;
import com.zhaokxkx13.dao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/3/16.
 */
@SpringBootTest(classes = StatApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
    }
}
