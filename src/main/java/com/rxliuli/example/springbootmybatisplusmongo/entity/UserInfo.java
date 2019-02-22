package com.rxliuli.example.springbootmybatisplusmongo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户信息
 * 使用数据库存储
 *
 * @author rxliuli
 */
@TableName("user_info")
public class UserInfo {
    @TableId
    private Long id;
    @TableField
    private String name;
    @TableField
    private Integer age;
    @TableField
    private Boolean sex;

    public UserInfo() {
    }

    public UserInfo(Long id, String name, Integer age, Boolean sex) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public UserInfo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserInfo setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserInfo setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Boolean getSex() {
        return sex;
    }

    public UserInfo setSex(Boolean sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
