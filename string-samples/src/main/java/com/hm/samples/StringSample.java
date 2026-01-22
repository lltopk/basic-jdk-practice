package com.hm.samples;

import java.util.ArrayList;
import java.util.List;

public class StringSample {
    public static void main(String[] args) {
        System.out.println("cannotChangeStringReference...");
        String str = new String("Hello");
        System.out.println("before str: "+str);
        cannotChangeStringReference(str);
        System.out.println("after str: "+str);

        System.out.println("canChangeStringBufferReference...");
        StringBuffer sb = new StringBuffer("Hello");
        System.out.println("before str: "+sb);
        canChangeStringBufferReference(sb);
        System.out.println("after str: "+sb);

    }

    /**
     * Java是值传递(引用的副本: str ref(copy))
     * str ref----------------
     *                       |
     *                       hello
     *                       |
     * str ref(copy)---------|
     *
     * 如果我们能够修改引用对象的内容, 就可以影响原引用, 但很不幸String没有提供修改自身对象内容的方法
     *
     * 即使是包装为StringBuilder也不是修改自身对象内容, 而是构建了新的对象
     *
     * 所以最终无法对引用字符串造成影响
     *
     * 即使最后一行str = sb.toString()也仅仅修改的方法内部副本的指向, 并不影响外部引用str的指向
     * str ref--------------hello
     *
     * str ref(copy)--------*hello
     * @param str
     */
    private static void cannotChangeStringReference(String str){
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, '*'); // 修改当前字符为 '*'
        str = sb.toString();
    }

    /**
     * 直接传递StringBuffer引用可以实现效果
     * @param sb
     */
    private static void canChangeStringBufferReference(StringBuffer sb){
        sb.setCharAt(0, '*'); // 修改当前字符为 '*'
    }

}
