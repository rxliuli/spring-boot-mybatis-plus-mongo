package com.rxliuli.example.springbootmybatisplusmongo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rxliuli.example.springbootmybatisplusmongo.dao.UserInfoDao;
import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfo;
import com.rxliuli.example.springbootmybatisplusmongo.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author rxliuli
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {

}
