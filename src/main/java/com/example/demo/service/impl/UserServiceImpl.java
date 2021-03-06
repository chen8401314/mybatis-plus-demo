package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.TestEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.TestMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenx
 * @since 2020-11-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserEntity findByUsername(String username) {
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.eq("username",username);
        return userMapper.selectOne(qw);
    }
}
