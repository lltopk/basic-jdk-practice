package com.hm.clsloadpractice.load;

public class TestClassForName {
    /**
     * Class.forName()
     *
     * 默认执行 加载 → 链接 → (类)初始化(非实例) 三个阶段。
     *
     * 会触发 static 静态代码块、静态变量赋值，并初始化父类。
     *
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.hm.clsloadpractice.load.User");
    }
}
