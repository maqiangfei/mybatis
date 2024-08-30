package com.maffy.service;

import com.maffy.App;
import com.maffy.entity.po.User;
import com.maffy.entity.po.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author 马强飞
 * @version 1.0
 * @since 7/18/2024 6:24 PM
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private IUserService userService;

    @Test
    void testSaveUser() {
        User user = new User();
        // user.setId(5L);
        user.setUsername("Rosy");
        user.setPassword("123");
        user.setPhone("19216914913");
        user.setBalance(200);
        // user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setInfo(UserInfo.of(24, "英文老师", "female"));

        this.userService.save(user);
    }


    @Test
    void testQuery() {
        List<User> users = userService.listByIds(List.of(1L, 2L, 4L));
        users.forEach(System.out::println);
    }
}