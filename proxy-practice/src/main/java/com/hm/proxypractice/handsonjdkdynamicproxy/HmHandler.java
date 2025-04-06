package com.hm.proxypractice.handsonjdkdynamicproxy;

/**
 * @author liuyanoutsee@outlook.com
 *
 * Handler接口传给动态代理工厂HmInterfaceFactory
 *
 * 动态代理工厂HmInterfaceFactory根据传入的
 * - functionBody
 * - setProxy
 * 来生成Java文件，生成编译后的class文件，以及class文件生成后的反射赋值动作
 *
 **/
public interface HmHandler {

    /**
     * @param methodName
     * @return
     */
    String functionBody(String methodName);

    // 为什么还要setProxy？因为尽管动态代理工厂在内存中生成了java文件，并编译成了class文件给到jvm, 但class中的自定义属性还为赋值，是空的。在运行时反射赋值，这就是反射的价值！
    // 为什么叫setProxy？因为给代理对象的属性赋值操作用的是反射，所以命名有反过来的意味
    default void setProxy(HmInterface proxy) {

    }
}
