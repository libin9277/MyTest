package com.example.hellodemo.util;

/**
 * @author libin
 * @date 2022年01月06日 10:17
 */
public enum DemoEnum {
    D1("1"),
    ;
    private String code;
    DemoEnum(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
