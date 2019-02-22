package com.rxliuli.example.springbootmybatisplusmongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户日志
 * 使用 mongodb 存储
 *
 * @author rxliuli
 */
@Document
public class UserInfoLog implements Serializable {
    @Id
    private Long id;
    @Field
    private Long userId;
    @Field
    private String operate;
    @Field
    private LocalDateTime logTime;

    public UserInfoLog() {
    }

    public UserInfoLog(Long id, Long userId, String operate, LocalDateTime logTime) {
        this.id = id;
        this.userId = userId;
        this.operate = operate;
        this.logTime = logTime;
    }

    public Long getId() {
        return id;
    }

    public UserInfoLog setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserInfoLog setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getOperate() {
        return operate;
    }

    public UserInfoLog setOperate(String operate) {
        this.operate = operate;
        return this;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public UserInfoLog setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserInfoLog{" + "id=" + id +
                ", userId=" + userId +
                ", operate='" + operate + '\'' +
                ", logTime=" + logTime +
                '}';
    }
}
