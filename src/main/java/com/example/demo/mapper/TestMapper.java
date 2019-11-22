package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2019-11-21
 */
public interface TestMapper extends BaseMapper<Test> {


    IPage<Test> findPage(Page page,@Param("name") String name);

    IPage<TestDTO> findPageDTO(Page page,@Param("name") String name);

    @Select("SELECT * FROM pf_test WHERE name like '%${name}%'")
    IPage<Test> selectPage1(Page<Test> page,@Param("name") String name);
}
