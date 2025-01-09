package com.lyflexi.basicpractice.protect.abstractFather;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: hm
 * @Date: 2025/1/9 20:42
 * @Project: basic-jdk-practice
 */
@Slf4j
public class ConcreteStrategy1 extends AbstractFather<JobBo1> {

    @Override
    protected void handle(JobBo1 jobBo1) {
        log.info("handle JobBo1: {}", jobBo1.toString());
    }
}
