package com.hm.clsloadpractice.load;

public class TestLoadClass {
    /**
     * ClassLoader.loadClass()
     *
     * 默认只执行 加载 → 链接，不初始化 类。
     *
     * 因此不会执行静态代码块或初始化静态变量，直到首次主动使用类（如 new 对象、调用静态方法、访问静态字段）才初始化。
     *
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader.getSystemClassLoader().loadClass("com.hm.clsloadpractice.load.User");
    }
}
