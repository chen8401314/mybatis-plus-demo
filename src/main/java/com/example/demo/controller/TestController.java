package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Response;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.Test;
import com.example.demo.mapper.TestMapper;
import com.example.demo.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenx
 * @since 2019-11-21
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @ApiOperation(value = "获取test")
    @GetMapping(value = "/findAll")
    public Response<List<Test>> findAll() {
        return Response.instance.success(testService.list());
    }

    @ApiOperation(value = "获取pagetest")
    @GetMapping(value = "/selectPage")
    public Response<IPage<Test>> selectPage(
            @RequestParam(required = false) Integer pages,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String name
    ) {
        Page<Test> page = new Page(pages,size);
        return Response.instance.success(testService.selectPage(name,page));
    }

    @ApiOperation(value = "获取pagetest")
    @GetMapping(value = "/selectPage1")
    public Response<IPage<Test>> selectPage1(
            @RequestParam(required = false) Integer pages,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String name
    ) {
        Page<Test> page = new Page(pages,size);
        return Response.instance.success(testService.selectPage1(name,page));
    }

    @ApiOperation(value = "获取pagetest")
    @GetMapping(value = "/findPage")
    public Response<IPage<Test>> findPage(
            @RequestParam(required = false) Integer pages,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String name
    ) {
        Page<Test> page = new Page(pages,size);
        return Response.instance.success(testService.findPage(name,page));
    }
}
