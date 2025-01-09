package com.lyflexi.basicpractice.protect.abstractFather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hm
 * @Date: 2025/1/9 20:42
 * @Project: basic-jdk-practice
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobBo2 {
    private String jobDescription;

    public String toString(){
        return jobDescription;
    }
}
