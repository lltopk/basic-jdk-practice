package com.lyflexi.basicpractice.staticSample.life;

/**
 * @Author: liuyanoutsee@outlook.com
 * @Date: 2025/4/11 22:47
 * @Project: basic-jdk-practice
 * @Version: 1.0.0
 * @Description: BizContextHolderV2将单例成员变量BizContextHolder, 进一步抽象为单例成员内部类，效果是一样的。
 *
 * 虽然多嵌套了一层内部类显得多余，但是命名为SingletonHolder的内部类更加简明之意
 *
 */
public class BizContextHolderV2 {

    //成员变量threadlocal
    private ThreadLocal<String> tl;

    public BizContextHolderV2(){
        tl = new InheritableThreadLocal<>();
    }

    public static BizContextHolderV2 getInstance(){
        return SingletonHolder.INSTANCE;
    }

    //单例Holder生成器: 内部单例类
    static class SingletonHolder {
        private static final BizContextHolderV2 INSTANCE = new BizContextHolderV2();
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
        BizContextHolderV2 bizContextHolderV1 = BizContextHolderV2.getInstance();
        bizContextHolderV1.setContext("Hello, World!");
        System.out.println(bizContextHolderV1.getContext()); // 输出: Hello, World!
    }
}
