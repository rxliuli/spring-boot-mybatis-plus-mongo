package com.rxliuli.example.springbootmybatisplusmongo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author rxliuli
 */
@SpringBootApplication
@MapperScan("com.rxliuli.example.springbootmybatisplusmongo.**.dao.**")
@EnableMongoRepositories("com.rxliuli.example.springbootmybatisplusmongo.**.repository.**")
public class SpringBootMybatisPlusMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisPlusMongoApplication.class, args);
    }

}
