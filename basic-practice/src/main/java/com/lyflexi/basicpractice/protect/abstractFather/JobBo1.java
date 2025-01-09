package com.lyflexi.basicpractice.protect.abstractFather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: hm
 * @Date: 2025/1/9 20:42
 * @Project: basic-jdk-practice
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class JobBo1 {
    private String jobDescription;

    public String toString(){
        return jobDescription;
    }

    private void test(){
        log.info("JobBo1 test");
    }
}
