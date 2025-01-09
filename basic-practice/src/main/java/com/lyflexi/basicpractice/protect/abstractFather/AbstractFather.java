package com.lyflexi.basicpractice.protect.abstractFather;

import java.util.Objects;

/**
 * @Author: hm
 * @Date: 2025/1/9 20:38
 * @Project: basic-jdk-practice
 */
public abstract class AbstractFather<T> {
    private Long id;
    private String name;
    protected void process(T t) {
        if(Objects.nonNull(t)){
            handle(t);
        }
    }
    protected abstract void handle(T t);
}
