package com.rxliuli.example.springbootmybatisplusmongo.repository;

import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfoLog;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author rxliuli
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoLogRepositoryTest {
    @Autowired
    private UserInfoLogRepository userInfoLogRepository;

    public void insert() {
        userInfoLogRepository.insert(Lists.newArrayList(
                new UserInfoLog(1L, 1L, "登录", LocalDateTime.now()),
                new UserInfoLog(2L, 1L, "退出", LocalDateTime.now()),
                new UserInfoLog(3L, 2L, "登录", LocalDateTime.now()),
                new UserInfoLog(4L, 3L, "退出", LocalDateTime.now())
        ));
    }

    @Test
    public void findUserInfoLogByIdEquals() {
        final UserInfoLog result = userInfoLogRepository.findUserInfoLogByIdEquals(1L);
        assertThat(result)
                .isNotNull();
    }

    @Test
    public void listByParam() {
        final UserInfoLog userInfoLog = new UserInfoLog(null, 1L, "登",
                LocalDateTime.parse("2019-02-22T08:22:16.000Z", DateTimeFormatter.ISO_DATE_TIME));
        final List<UserInfoLog> result = userInfoLogRepository.listByParam(userInfoLog);
        assertThat(result)
                .isNotEmpty()
                .allMatch(log ->
                        Objects.equals(userInfoLog.getUserId(), log.getUserId())
                                && log.getOperate().contains(userInfoLog.getOperate())
                                && log.getLogTime().isAfter(userInfoLog.getLogTime())
                );
    }

    @Test
    public void findUserInfoLogByUserIdEqualsAndLogTimeGreaterThanEqualAndOperateRegex() {
        final UserInfoLog userInfoLog = new UserInfoLog(null, 1L, "登",
                LocalDateTime.parse("2019-02-22T08:22:16.000Z", DateTimeFormatter.ISO_DATE_TIME));
        final List<UserInfoLog> result = userInfoLogRepository.findUserInfoLogsByUserIdEqualsAndLogTimeGreaterThanEqualAndOperateRegex(userInfoLog.getUserId(), userInfoLog.getLogTime(), userInfoLog.getOperate());
        assertThat(result)
                .isNotEmpty()
                .allMatch(log ->
                        Objects.equals(userInfoLog.getUserId(), log.getUserId())
                                && log.getOperate().contains(userInfoLog.getOperate())
                                && log.getLogTime().isAfter(userInfoLog.getLogTime())
                );
    }
}