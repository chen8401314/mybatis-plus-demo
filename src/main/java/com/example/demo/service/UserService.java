package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenx
 * @since 2020-11-09
 */
public interface UserService extends IService<UserEntity> {

    UserEntity findByUsername(String username);
}
