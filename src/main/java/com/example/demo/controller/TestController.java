package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Response;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.TestEntity;
import com.example.demo.service.TestService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;

    @ApiOperation(value = "保存test")
    @PostMapping(value = "/save")
    public Response<String> save(@RequestBody TestEntity test) {
        testService.save(test);
        return Response.success();
    }

    @ApiOperation(value = "获取test")
    @GetMapping(value = "/findAll")
    public Response<List<TestEntity>> findAll() {
        return Response.success(testService.list());
    }

    @ApiOperation(value = "获取pagetest")
    @GetMapping(value = "/selectPage")
    public Response<IPage<TestEntity>> selectPage(
            @RequestParam(required = false) Integer pages,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String name
    ) {
        Page<TestEntity> page = new Page(pages,size);
        return Response.success(testService.selectPage(name,page));
    }

    @ApiOperation(value = "获取pagetest")
    @GetMapping(value = "/selectPage1")
    public Response<IPage<TestDTO>> selectPage1(
            @RequestParam(required = false) Integer pages,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String name
    ) {
        Page<TestDTO> page = new Page(pages,size);
        return Response.success(testService.selectPage1(name,page));
    }

    @ApiOperation(value = "获取pagetest")
    @GetMapping(value = "/findPage")
    public Response<IPage<TestEntity>> findPage(
            @RequestParam(required = false) Integer pages,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String name
    ) {
        Page<TestEntity> page = new Page(pages,size);
        return Response.success(testService.findPage(name,page));
    }

    @ApiOperation(value = "获取pagetestDTO")
    @GetMapping(value = "/findPageDTO")
    public Response<IPage<TestDTO>> findPageDTO(
            @RequestParam(required = false) Integer pages,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String name
    ) {
        Page<TestEntity> page = new Page(pages,size);
        return Response.success(testService.findPageDTO(name,page));
    }
}
