package com.hm.eippractice.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/11/2 15:42
 * @description
 */
@Slf4j
public class Father {


    private String key = "father";

    public String getKey() {
        return key;
    }

    public void Father(){
        this.privateMethod();
    }
    public void publicMethod() {
        log.info("father publicMethod");
    }
    protected void protectedMethod() {
        log.info("father protectedMethod");
    }
    private void privateMethod() {
        log.info("father privateMethod");
    }
}
