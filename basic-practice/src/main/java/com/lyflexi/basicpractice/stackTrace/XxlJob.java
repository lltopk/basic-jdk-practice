package com.lyflexi.basicpractice.stackTrace;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/22 14:57
 */
// 定义一个注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface XxlJob {

        String value();

}
