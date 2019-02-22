package com.rxliuli.example.springbootmybatisplusmongo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rxliuli.example.springbootmybatisplusmongo.dao.UserInfoDao;
import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfo;
import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfoLog;
import com.rxliuli.example.springbootmybatisplusmongo.repository.UserInfoLogRepository;
import com.rxliuli.example.springbootmybatisplusmongo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 同时使用 Mybatis Dao 和 MongoDB Repository
 *
 * @author rxliuli
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {
    @Autowired
    private UserInfoLogRepository userInfoLogRepository;

    @Override
    public Map<UserInfo, List<UserInfoLog>> listUserInfoAndLogMap() {
        final List<UserInfo> userInfoList = list();
        final List<UserInfoLog> userInfoLogList = userInfoLogRepository.findAll();
        final Map<Long, List<UserInfoLog>> map = userInfoLogList.stream().collect(Collectors.groupingBy(UserInfoLog::getUserId));
        return userInfoList.stream()
                .collect(Collectors.toMap(user -> user, user -> map.getOrDefault(user.getId(), Collections.emptyList())));
    }
}
