package com.rxliuli.example.springbootmybatisplusmongo.repository;

import com.rxliuli.example.springbootmybatisplusmongo.entity.UserInfoLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 定义一些简单操作的 Dao 接口
 *
 * @author rxliuli
 */
public interface UserInfoLogRepository extends MongoRepository<UserInfoLog, Long>, CustomUserInfoLogRepository {
    /**
     * 根据 id 查询用户日志信息
     *
     * @param id 查询的 id
     * @return 用户日志
     */
    @Nullable
    UserInfoLog findUserInfoLogByIdEquals(Long id);

    /**
     * 根据用户 id/记录时间/操作说明查询用户日志
     *
     * @param userId  用户 id
     * @param logTime 记录时间
     * @param operate 操作说明
     * @return 用户日志
     */
    List<UserInfoLog> findUserInfoLogsByUserIdEqualsAndLogTimeGreaterThanEqualAndOperateRegex(Long userId, LocalDateTime logTime, String operate);
}
