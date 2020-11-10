package com.example.demo.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.example.demo.context.BaseEnumDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@JsonDeserialize(using = BaseEnumDeserializer.class)
public enum StatusEnum implements IEnum<Integer> {
    /**
     *
     */
    VALID(1,"可用"),
    INVALID(0,"不可用");
    @EnumValue
    private  Integer value;
    private  String msg;

    StatusEnum(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    @JsonCreator
    public static StatusEnum fromValue(Integer value) {
        for (StatusEnum s : StatusEnum.values()) {
            if (s.value.equals(value)) {
                return s;
            }
        }
        return VALID;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
