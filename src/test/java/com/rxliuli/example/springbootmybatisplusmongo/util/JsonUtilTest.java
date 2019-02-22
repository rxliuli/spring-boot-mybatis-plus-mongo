package com.rxliuli.example.springbootmybatisplusmongo.util;

import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author rxliuli
 */
public class JsonUtilTest {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void toJson() {
        final String result = JsonUtil.toJson(new UserInfo(1L, "rx", 17, false));
        log.info("result: {}", result);
        log.info("LocalDateTime: {}", JsonUtil.toJson(LocalDateTime.now()));
        log.info("Date: {}", JsonUtil.toJson(new Date()));
    }
}