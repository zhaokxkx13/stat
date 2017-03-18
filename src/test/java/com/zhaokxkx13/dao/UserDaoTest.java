package com.zhaokxkx13.dao;

import com.zhaokxkx13.StatApplication;
import com.zhaokxkx13.dao.entity.Role;
import com.zhaokxkx13.dao.entity.User;
import com.zhaokxkx13.dao.inf.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatApplication.class)
public class UserDaoTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelect() {
        User user = userMapper.selectRoleByUserName("zhao");
        List<Role> roleList = user.getRoleList();
        for (Role item : roleList) {
            System.out.println(item.getDescription());
        }
    }
}
