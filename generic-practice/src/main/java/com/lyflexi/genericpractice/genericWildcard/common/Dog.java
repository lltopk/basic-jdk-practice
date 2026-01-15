package com.lyflexi.genericpractice.genericWildcard.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/23 17:39
 */
@Data
@AllArgsConstructor
public class Dog extends AbstractAnimal {

    @Override
    public void print() {
        System.out.println("Dog: "+name);
    }
}
