package com.hm.proxypractice.handsonjdkdynamicproxy;

public class HmInterface$proxy3 implements HmInterface {
 HmInterface hmInterface;
    @Override
    public void func1() {
         System.out.println("before");
        hmInterface.func1();
        System.out.println("after");
    }

    @Override
    public void func2() {
         System.out.println("before");
        hmInterface.func2();
        System.out.println("after");
    }

    @Override
    public void func3() {
         System.out.println("before");
        hmInterface.func3();
        System.out.println("after");
    }
}
