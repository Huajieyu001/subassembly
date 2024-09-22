package com.itheima.mp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserStatus {

    NORMAL(1, "正常"),
    FREEZE(2, "冻结"),;

    @EnumValue // 标记对应DB中的值
    private final int value;
    @JsonValue // 标记的字段用于返回给前台，不标记则默认把枚举的名称转为字符串返回，比如"NORMAL"
    private final String desc;

    UserStatus(int value, String desc){
        this.value = value;
        this.desc = desc;
    }
}
