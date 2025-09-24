package com.lyflexi.genericpractice.genericT;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/9/24 17:01
 */
public class TestStatic<T> {
    public static void main(String [] arags)
    {
        TestStatic<Double> d = new TestStatic<Double>();

        d.method(3.4);

        d.staticMethod(true);

        d.show("haha");

        d.print(4L);
    }


    public static <T> void method(T t)
    {
        System.out.println("method :"+ t);
    }
    //如果静态方法操作的应用数据类型不确定，可以将泛型定义在方法上
    public static <W> void staticMethod(W w)
    {
        System.out.println("staticMethod :"+ w);
    }
    //如果静态方法操作的应用数据类型不确定，可以将泛型定义在方法上
    public <K> void show( K  k)
    {
        System.out.println("Show :"+ k);
    }
    //如果静态方法操作的应用数据类型不确定，可以将泛型定义在方法上
    public <Q> void print(Q q)
    {
        System.out.println("Print :"+ q);
    }

}
