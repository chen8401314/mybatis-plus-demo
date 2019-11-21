package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Test;
import com.example.demo.mapper.TestMapper;
import com.example.demo.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.catalina.Store;
import org.apache.ibatis.annotations.Param;
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
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {


    @Autowired
    TestMapper testMapper;

    @Override
    public IPage<Test> findPage(String name,Page<Test> page) {
        return testMapper.findPage(page,name);
    }

    @Override
    public IPage<Test> selectPage(String name,Page<Test> page) {
        QueryWrapper<Test> qw = new QueryWrapper<>();
        qw.likeRight("name",name);
        return testMapper.selectPage(page,qw);
    }
    @Override
    public IPage<Test> selectPage1(String name, Page<Test> page){
        return testMapper.selectPage1(page,name);
    }
}
