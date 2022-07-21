package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.TestEntity;
import com.example.demo.mapper.TestMapper;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenx
 * @since 2019-11-21
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestEntity> implements TestService {


    @Autowired
    TestMapper testMapper;

    @Override
    public IPage<TestEntity> findPage(String name,Page<TestEntity> page) {
        return testMapper.findPage(page,name);
    }

    @Override
    public IPage<TestDTO> findPageDTO(String name,Page<TestEntity> page) {
        return testMapper.findPageDTO(page,name);
    }

    @Override
    public IPage<TestEntity> selectPage(String name,Page<TestEntity> page) {
        QueryWrapper<TestEntity> qw = new QueryWrapper<>();
        qw.likeRight("name",name);
        return testMapper.selectPage(page,qw);
    }
    @Override
    public IPage<TestDTO> selectPage1(String name, Page<TestDTO> page){
        return testMapper.selectPage1(page,name);
    }
}
