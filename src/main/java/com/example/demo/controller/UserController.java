package com.example.demo.controller;


import com.example.demo.common.Response;
import com.example.demo.entity.UserEntity;
import com.example.demo.request.LoginReq;
import com.example.demo.service.UserService;
import com.example.demo.util.HttpReqUtil;
import com.example.demo.util.JWTUtils;
import com.example.demo.util.SecurityUtil;
import com.google.common.base.Throwables;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *
 * </p>
 *
 * @author chenx
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "保存user")
    @PostMapping(value = "/save")
    public Response<String> save(@RequestBody UserEntity user) {
        user.setPassword(SecurityUtil.getMD5(user.getPassword()));
        userService.save(user);
        return Response.success();
    }

    @PostMapping(value = "/anon/login")
    @ApiOperation(value = "用户登陆")
    public Response login(@RequestBody @Validated LoginReq req, HttpServletResponse response) {
        try {
            // 对传入的密码进行解密
            UserEntity userEntity = userService.findByUsername(req.getUsername());
            if (userEntity == null) {
                return Response.failure("该用户不存在！");
            }
            if (!StringUtils.equals(userEntity.getPassword(), SecurityUtil.getMD5(req.getPassword()))) {
                log.info("账号密码错误");
                return Response.failure("账号密码错误");
            }
            String jwt = "";
            //如果是移动端登录token失效时间为一年
            jwt = JWTUtils.createToken(userEntity.getId(), userEntity.getUsername());
            HttpReqUtil.setTokenCookies(jwt, response);
            return Response.success();
        } catch (Exception e) {
            log.info(Throwables.getStackTraceAsString(e));
            return Response.failure(e.getMessage());
        }
    }

}
