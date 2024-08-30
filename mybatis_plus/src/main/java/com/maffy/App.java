package com.maffy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/17/2024 8:27 PM
 */
@SpringBootApplication
@MapperScan("com.maffy.mapper")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
