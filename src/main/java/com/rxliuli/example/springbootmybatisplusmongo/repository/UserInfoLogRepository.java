package com.rxliuli.example.springbootmybatisplusmongo.repository;

import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfoLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 定义一些简单操作的 Dao 接口
 *
 * @author rxliuli
 */
@Repository
public interface UserInfoLogRepository extends MongoRepository<UserInfoLog, Long>, CustomUserInfoLogRepository {
    /**
     * 根据 id 查询用户日志信息
     *
     * @param id 查询的 id
     * @return 用户日志
     */
    UserInfoLog findUserInfoLogByIdEquals(Long id);
}
