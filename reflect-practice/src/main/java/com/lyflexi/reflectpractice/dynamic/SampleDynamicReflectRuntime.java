package com.lyflexi.reflectpractice.dynamic;

import java.lang.reflect.Method;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/8/2 23:11
 */
public class SampleDynamicReflectRuntime {
    /**
     * 输入入口
     * 反射相较于静态编译，体现在运行时&&动态
     *
     * @param args
     */
    public static void main(String[] args) {
        //有参构造函数的动态反射
        processDynamicReflectInRuntimeWithArgConstructor("com.lyflexi.reflectpractice.dynamic.BizLogic1", 1L, "Dynamic Reflect Example 1","doSomething");
        processDynamicReflectInRuntimeWithArgConstructor("com.lyflexi.reflectpractice.dynamic.BizLogic2", 1L, "Dynamic Reflect Example 2","doSomething");

        //无参构造函数的动态反射
        processDynamicReflectInRuntimeWithNoArgConstructor("com.lyflexi.reflectpractice.dynamic.BizLogic1", "doSomething");
        processDynamicReflectInRuntimeWithNoArgConstructor("com.lyflexi.reflectpractice.dynamic.BizLogic2", "doSomething");

    }

    /**
     * 动态反射在运行时创建实例并调用方法
     * 反射相较于静态编译，体现在运行时&&动态语言，赋予了Java更强大的灵活性和动态性。
     * eg.无参构造函数的动态反射
     * @param className 类名
     * @param methodName 方法名
     */
    private static void processDynamicReflectInRuntimeWithNoArgConstructor(String className, String methodName) {
        try {
            // 动态加载类
            Class<?> clazz = Class.forName(className);
            // 创建实例
            Object instance = clazz.newInstance();
            // 调用方法
            Method method = clazz.getMethod(methodName,String.class);
            method.invoke(instance,"Executing dynamic reflect method");
            System.out.println("Instance created and method invoked: " + instance);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 动态反射在运行时创建实例并调用方法
     * 反射相较于静态编译，体现在运行时&&动态语言，赋予了Java更强大的灵活性和动态性。
     * eg.有参构造函数的动态反射
     * @param className 类名
     * @param id 实例ID
     * @param name 实例名称
     * @param methodName 方法名
     */
    private static void processDynamicReflectInRuntimeWithArgConstructor(String className, Long id, String name,String methodName) {
        try {
            // 动态加载类
            Class<?> clazz = Class.forName(className);
            // 创建实例
            Object instance = clazz.getDeclaredConstructor(Long.class, String.class).newInstance(id, name);
            // 调用方法
            Method method = clazz.getMethod(methodName,String.class);
            method.invoke(instance,"Executing dynamic reflect method");
            System.out.println("Instance created and method invoked: " + instance);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
