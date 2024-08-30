package com.maffy.test;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maffy.entity.po.User;
import com.maffy.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/20/2024 6:22 PM
 */
@SpringBootTest
public class PageQueryTest {

    @Resource
    private IUserService userService;

    @Test
    void testPageQuery() {
        // 分页条件
        int pageNo = 1, pageSize = 2;
        Page<User> page = Page.of(pageNo, pageSize);
        // 排序条件
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("balance");
        orderItem.setAsc(true);
        page.addOrder(orderItem);
        // 分页查询
        Page<User> p = userService.page(page);
        // 解析
        long total = p.getTotal();
        System.out.println("total = " + total);
        long pages = p.getPages();
        System.out.println("pages = " + pages);
        List<User> users = p.getRecords();
        users.forEach(System.out::println);
    }
}
