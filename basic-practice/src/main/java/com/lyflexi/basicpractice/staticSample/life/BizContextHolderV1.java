package com.lyflexi.basicpractice.staticSample.life;

/**
 * @Author: liuyanoutsee@outlook.com
 * @Date: 2025/4/11 22:47
 * @Project: basic-jdk-practice
 * @Version: 1.0.0
 * @Description: static变量会在类加载的时候进行初始化，并被所有实例共享，因此利用此特性结合final修饰符，可以实现单例模式
 *
 * 注意单例指的是当前的BizContextHolder，而不是threadlocal变量
 */
public class BizContextHolderV1 {
    //单例Holder：静态final成员变量
    private static final BizContextHolderV1 INSTANCE = new BizContextHolderV1();

    //成员变量threadlocal
    private ThreadLocal<String> tl;

    public BizContextHolderV1(){
        //InheritableThreadLocal是一个线程局部变量，它允许子线程继承父线程的值。
        tl = new InheritableThreadLocal<>();
    }

    public static BizContextHolderV1 getInstance(){
        return INSTANCE;
    }

    /**
     * 获取上下文中的信息
     * @return
     */
    public String getContext(){
        return tl.get();
    }

    /**
     * 设置上下文中的信息
     * @param context
     */
    public void setContext(String context){
        tl.set(context);
    }

    public static void main(String[] args) {
        //获取单实例BizContextHolder
        BizContextHolderV1 bizContextHolderV1 = BizContextHolderV1.getInstance();
        bizContextHolderV1.setContext("Hello, World!");
        System.out.println(bizContextHolderV1.getContext()); // 输出: Hello, World!
    }
}
