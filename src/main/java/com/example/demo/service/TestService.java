package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.TestEntity;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenx
 * @since 2019-11-21
 */
public interface TestService extends IService<TestEntity> {

    IPage<TestEntity> selectPage(String name,Page<TestEntity> page);

    IPage<TestEntity> findPage(String name,Page<TestEntity> page);

    IPage<TestDTO> findPageDTO(String name,Page<TestEntity> page);

    IPage<TestDTO>  selectPage1(String name, Page<TestDTO> page);
}
