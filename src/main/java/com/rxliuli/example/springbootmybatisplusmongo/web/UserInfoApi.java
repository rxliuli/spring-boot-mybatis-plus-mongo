package com.rxliuli.example.springbootmybatisplusmongo.web;

import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfo;
import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfoLog;
import com.rxliuli.example.springbootmybatisplusmongo.service.UserInfoService;
import com.rxliuli.example.springbootmybatisplusmongo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户信息的 api
 *
 * @author rxliuli
 */
@RestController
@RequestMapping("/api/user-info")
public class UserInfoApi {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/list")
    public List<UserInfo> list() {
        return userInfoService.list();
    }

    @GetMapping("/list-user-info-and-log-map")
    public Map<String, List<UserInfoLog>> listUserInfoAndLogMap() {
        return userInfoService.listUserInfoAndLogMap().entrySet().stream()
                .collect(Collectors.toMap(kv -> JsonUtil.toJson(kv.getKey()), Map.Entry::getValue));
    }
}
