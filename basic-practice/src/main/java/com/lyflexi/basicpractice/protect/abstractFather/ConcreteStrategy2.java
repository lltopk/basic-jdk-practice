package com.lyflexi.basicpractice.protect.abstractFather;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: hm
 * @Date: 2025/1/9 20:42
 * @Project: basic-jdk-practice
 */
@Slf4j
public class ConcreteStrategy2 extends AbstractFather<JobBo2> {

    @Override
    protected void handle(JobBo2 jobBo2) {
        log.info("handle JobBo2: {}", jobBo2.toString());
    }
}
