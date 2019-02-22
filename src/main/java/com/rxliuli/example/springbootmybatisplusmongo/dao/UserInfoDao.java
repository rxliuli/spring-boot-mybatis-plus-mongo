package com.rxliuli.example.springbootmybatisplusmongo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author rxliuli
 */
@Repository
public interface UserInfoDao extends BaseMapper<UserInfo> {
}
