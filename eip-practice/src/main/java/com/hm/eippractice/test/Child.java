package com.hm.eippractice.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/11/2 15:43
 * @description
 */
@Slf4j
public class Child extends Father{

    public void init(){
        this.protectedMethod();
    }

    @Override
    protected void protectedMethod(){
        log.info("child protectedMethod");
    }
}
