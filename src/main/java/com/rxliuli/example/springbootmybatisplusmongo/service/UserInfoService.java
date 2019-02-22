package com.rxliuli.example.springbootmybatisplusmongo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfo;
import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfoLog;

import java.util.List;
import java.util.Map;

/**
 * @author rxliuli
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 获取用户信息与用户日志的映射表
     *
     * @return 以 {@link UserInfo} -> {@link List<UserInfoLog>} 形式的 {@link Map}
     */
    Map<UserInfo, List<UserInfoLog>> listUserInfoAndLogMap();
}
