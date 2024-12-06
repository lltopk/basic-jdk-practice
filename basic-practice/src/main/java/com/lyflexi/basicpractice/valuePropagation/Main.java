package com.lyflexi.basicpractice.valuePropagation;

/**
 * @Author: ly
 * @Date: 2024/4/1 10:30
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("----------------testBasicValue");
        int number = 10;
        System.out.println("Before calling testBasicValue: " + number);
        testBasicValue(number);
        System.out.println("After calling testBasicValue: " + number);

        System.out.println("----------------testWrapperValue");
        Integer numberInteger = 10;
        System.out.println("Before calling testWrapperValue: " + number);
        testWrapperValue(numberInteger);
        System.out.println("After calling testWrapperValue: " + number);

        System.out.println("----------------testReference");
        ObjectReference reference = new ObjectReference(10);
        System.out.println("Before calling testReference: " + reference);
        testReference(reference);
        System.out.println("After calling testReference: " + reference);
    }

    /**
     * 值传递：方法修改不影响传入值
     *
     * 改完值还是10
     * @param num
     */
    public static void testBasicValue(int num) {
        num = 20;
    }

    /**
     * 值传递：方法修改不影响传入值
     *
     * 改完值还是10
     * @param num
     */
    public static void testWrapperValue(Integer num) {
        num = 20;
    }

    /**
     * 值传递：引用副本也是值
     *
     * 传入10，相当于传入了引用副本为10，内部改为20 ，则外部也改为20
     *
     * 但最终新创建的ObjectReference不属于同一引用，因此30不影响原来的引用
     * @param reference
     */
    public static void testReference(ObjectReference reference) {
        reference.setValue(20);
        reference = new ObjectReference();
        reference.setValue(30);
    }
}

