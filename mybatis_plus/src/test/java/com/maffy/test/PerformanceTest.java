package com.maffy.test;

import com.maffy.App;
import com.maffy.entity.po.User;
import com.maffy.entity.po.UserInfo;
import com.maffy.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/20/2024 10:31 AM
 */
@SpringBootTest
public class PerformanceTest {

    @Resource
    private IUserService userService;

    private User builderUser(int i) {
        User user = new User();
        user.setUsername("user_" + i);
        user.setPassword("123");
        user.setPhone("" + (18688190000L + i));
        user.setBalance(2000);
        // user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setInfo(UserInfo.of(24, "英文老师", "female"));

        return user;
    }

    /**
     * 每一个sql都发一次网络请求
     * 201,460ms
     */
    @Test
    void testSaveOneByOne() {
        long b = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            userService.save(builderUser(i));
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - b));
    }

    /**
     * 每1000个sql才一个网络请求，但是数据库收到请求后还是一条一条sql执行，解决方案：jdbc：mysql驱动有一个参数rewriteBatchedStatements=true
     * 13,627ms -- rewriteBatchedStatements=false
     * 8,308ms  -- rewriteBatchedStatements=true
     */
    @Test
    void testSaveBatch() {
        // 每次批量插入1000条，插入100次

        // 1.准备一个容量为1000的集合
        List<User> list = new ArrayList<>(1000);
        long b = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            // 2.添加一个user
            list.add(builderUser(i));
            // 3.每1000条批量插入一次
            if (i % 1000 == 0) {
                userService.saveBatch(list); //提交的是一条一条的sql
                // 4.清空集合，准备下一批数据
                list.clear();
            }
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e - b));
    }
}
