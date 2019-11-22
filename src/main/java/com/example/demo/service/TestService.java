package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenx
 * @since 2019-11-21
 */
public interface TestService extends IService<Test> {

    IPage<Test> selectPage(String name,Page<Test> page);

    IPage<Test> findPage(String name,Page<Test> page);

    IPage<TestDTO> findPageDTO(String name,Page<Test> page);

    IPage<Test>  selectPage1(@Param("name") String name, Page<Test> page);
}
