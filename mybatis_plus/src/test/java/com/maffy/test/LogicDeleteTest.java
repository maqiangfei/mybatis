package com.maffy.test;

import com.maffy.App;
import com.maffy.entity.po.Address;
import com.maffy.service.IAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/20/2024 2:41 PM
 */
@SpringBootTest
public class LogicDeleteTest {

    @Autowired
    private IAddressService addressService;

    @Test
    void testLogicDelete() {
        // 删除
        addressService.removeById(7L);

        // 查询
        Address address = addressService.getById(7L);
        System.out.println("address = " + address);
    }
}
