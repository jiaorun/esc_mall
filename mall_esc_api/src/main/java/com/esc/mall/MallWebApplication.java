package com.esc.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 * @author jiaorun
 * @date 2021/09/02 16:36
 **/
@SpringBootApplication
@MapperScan(basePackages = {"com.esc.mall.mapper", "com.esc.mall.dao", "com.esc.mall.api.common"})
@EnableTransactionManagement
@EnableSwagger2
public class MallWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallWebApplication.class, args);
    }
}
