package com.example.demo.request;

import com.example.demo.enumeration.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class TestReq {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "名字")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "家庭住址")
    private String homeAddress;

    @ApiModelProperty(value = "是否结婚(1 是 0 否)")
    private Boolean isMarry;

    @ApiModelProperty(value = "照片")
    private String photo;

    @ApiModelProperty(value = "状态")
    private StatusEnum status;
}
