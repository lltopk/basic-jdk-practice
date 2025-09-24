package com.lyflexi.genericpractice.genericT;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/9/24 17:01
 */
public class Test<T> {
    // 该方法只是使用了泛型类定义的类型参数，不是泛型方法
    public void testMethod(T t){
        System.out.println(t);
    }
//    // <T> 声明了下面的方法是一个泛型方法
//    public <T> T testMethod1(T t){
//        return t;
//    }
    //省略泛型方法声明<T> 也可以
    public T testMethod1(T t){
        return t;
    }
}
