package com.lyflexi.genericpractice.genericWildcard.common;

import lombok.Data;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/23 17:40
 */
@Data
public class Husky extends AbstractAnimal{

    @Override
    public void print() {
        System.out.println("Husky :"+name);
    }
}
