package com.rxliuli.example.springbootmybatisplusmongo.repository;

import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfoLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * 数据仓库 {@link UserInfoLogRepository} 的实现类，但请务必注意，实现类继承的是 {@link CustomUserInfoLogRepository} 接口，而非本应该继承的接口
 *
 * @author rxliuli
 */
public class UserInfoLogRepositoryImpl implements CustomUserInfoLogRepository {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<UserInfoLog> listByParam(UserInfoLog userInfoLog) {
        final Criteria criteria = new Criteria();
        if (userInfoLog.getUserId() != null) {
            criteria.and("userId")
                    .is(userInfoLog.getUserId());
        }
        if (userInfoLog.getLogTime() != null) {
            criteria.and("logTime")
                    .gte(userInfoLog.getLogTime());
        }
        if (userInfoLog.getOperate() != null) {
            criteria.and("operate")
                    .regex(userInfoLog.getOperate());
        }
        return mongoOperations.find(new Query(criteria), UserInfoLog.class);
    }
}
