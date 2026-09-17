package com.hm.clsloadpractice.load;

public class User {

    /**
     * 初始化才会执行
     */
    static {
        System.out.println("User static block");
    }

    /**
     * 类加载就会执行
     */
    public User() {
        System.out.println("User constructor");
    }
}