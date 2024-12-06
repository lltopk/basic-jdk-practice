package com.lyflexi.basicpractice.copy.shallowCopyBeanUtils;

import com.alibaba.fastjson2.JSON;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/12/6 9:52
 */

public class Main  {

    public static void main(String[] args) {
//        testShallow1();
//        testShallow2();
        testDeep();
    }

    /**
     * 浅拷贝情况case1：翠山自刎，无忌也挂了
     */
    private static void testShallow1(){
        Father cuishan = new Father();
        cuishan.setFace("handsome");
        cuishan.setHeight("180");
        Life cuishanLife = new Life();
        cuishanLife.setStatus("alive");
        cuishan.setLife(cuishanLife);
        Son wuji=new Son();
        BeanUtils.copyProperties(cuishan,wuji);

        Life wujiLife = wuji.getLife();
        wujiLife.setStatus("alive");
        wuji.setLife(wujiLife);
        cuishanLife.setStatus("dead"); // 翠山后来自刎了

        System.out.println(JSON.toJSONString(cuishan));
        System.out.println(JSON.toJSONString(wuji));
    }
    /**
     * 浅拷贝情况case2：翠山自刎，无忌设置活着，翠山又活了
     */
    private static void testShallow2(){
        Father cuishan = new Father();
        cuishan.setFace("handsome");
        cuishan.setHeight("180");
        Life cuishanLife = new Life();
        cuishanLife.setStatus("alive");
        cuishan.setLife(cuishanLife);
        Son wuji=new Son();
        BeanUtils.copyProperties(cuishan,wuji);

        cuishanLife.setStatus("dead"); // 翠山后来自刎了
        Life wujiLife = wuji.getLife();
        wujiLife.setStatus("alive");
        wuji.setLife(wujiLife);


        System.out.println(JSON.toJSONString(cuishan));
        System.out.println(JSON.toJSONString(wuji));
    }

    /**
     * case3： 深拷贝，son重新创建新的life
     */
    private static void testDeep(){
        Father cuishan = new Father();
        cuishan.setFace("handsome");
        cuishan.setHeight("180");
        Life cuishanLife = new Life();
        cuishanLife.setStatus("alive");
        cuishan.setLife(cuishanLife);
        Son wuji=new Son();
        BeanUtils.copyProperties(cuishan,wuji);

        cuishanLife.setStatus("dead"); // 翠山自刎了  该行放在上下均可
        // 无忌用个新对象 不受翠山影响了
        Life wujiLife = new Life();
        wujiLife.setStatus("alive");
        wuji.setLife(wujiLife);


        System.out.println(JSON.toJSONString(cuishan));
        System.out.println(JSON.toJSONString(wuji));
    }
}
