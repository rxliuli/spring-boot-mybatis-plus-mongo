package com.rxliuli.example.springbootmybatisplusmongo.service;

import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author rxliuli
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceTest {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void list() {
        final List<UserInfo> list = userInfoService.list();
        assertThat(list)
                .isNotEmpty();
    }
}