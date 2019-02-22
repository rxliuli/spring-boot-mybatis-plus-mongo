package com.rxliuli.example.springbootmybatisplusmongo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rxliuli
 */
@SpringBootApplication
@MapperScan("com.rxliuli.example.springbootmybatisplusmongo.**.dao.**")
public class SpringBootMybatisPlusMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisPlusMongoApplication.class, args);
    }

}
