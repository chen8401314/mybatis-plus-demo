package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author chenx
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pf_user")
@ApiModel(value = "UserEntity对象", description = "")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String password;


}
