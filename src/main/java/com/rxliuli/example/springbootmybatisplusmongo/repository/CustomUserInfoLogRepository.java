package com.rxliuli.example.springbootmybatisplusmongo.repository;

import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfoLog;

import java.util.List;

/**
 * 自定义更加复杂的 Dao 接口
 *
 * @author rxliuli
 */
public interface CustomUserInfoLogRepository {
    /**
     * 根据一些参数查询用户信息列表
     *
     * @param userInfoLog 参数对象
     * @return 用户信息列表
     */
    List<UserInfoLog> listByParam(UserInfoLog userInfoLog);
}
