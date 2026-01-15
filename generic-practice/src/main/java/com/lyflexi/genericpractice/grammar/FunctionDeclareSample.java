package com.lyflexi.genericpractice.grammar;

/**
 * @author hasee
 * @description V1.0 泛型类
 * @create 2025/9/24 17:01
 */
public class FunctionDeclareSample<T> {
    /**
     * 泛型方法
     * @param t
     */
//    // 该方法只是使用了泛型类定义的类型参数，不是泛型方法
//    public void testMethod(T t){
//        System.out.println(t);
//    }
    // <T> 声明了下面的方法是一个泛型方法
    public <T> T testMethod1(T t){
        return t;
    }
    //省略泛型方法声明<T> 也可以
    public T testMethod2(T t){
        return t;
    }

    /**
     * 静态泛型函数
     * @param arags
     */
    public static void main(String [] arags)
    {
        FunctionDeclareSample<Double> instance = new FunctionDeclareSample<Double>();

        FunctionDeclareSample.method(3.4);

        FunctionDeclareSample.staticMethod(true);

        instance.show("haha");

        System.out.println(instance.testMethod1(4L));

        System.out.println(instance.testMethod2(3.4));
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

