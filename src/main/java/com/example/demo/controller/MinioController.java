package com.example.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Response;
import com.example.demo.config.MinIOTemplate;
import com.example.demo.dto.FileDTO;
import com.example.demo.dto.TestDTO;
import com.example.demo.entity.TestEntity;
import com.example.demo.request.TestReq;
import com.example.demo.service.TestService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.example.demo.structs.TestMap.TEST_MAPPER;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chenx
 * @since 2019-11-21
 */
@RestController
@RequestMapping("/minio")
@Slf4j
public class MinioController {
    @Autowired
    private MinIOTemplate minIOTemplate;

    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/uploadFile")
    public Response<FileDTO> save(@RequestParam("file") MultipartFile file) {
        try {
            return Response.success(minIOTemplate.uploadFile("", file.getOriginalFilename(), file.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
