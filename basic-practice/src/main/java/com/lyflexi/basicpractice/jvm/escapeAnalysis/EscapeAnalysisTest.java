package com.lyflexi.basicpractice.jvm.escapeAnalysis;

public class EscapeAnalysisTest {

    public static Object globalVariableObject;

    public Object instanceObject;

    public void globalVariableEscape(){
        globalVariableObject = new Object();  // 静态变量，外部线程可见，发生逃逸
    }

    public void instanceObjectEscape(){
        instanceObject = new Object();  // 赋值给堆中实例字段，外部线程可见，发生逃逸
    }

    public Object returnObjectEscape(){
        return new Object();   // 返回实例，外部线程可见，发生逃逸
    }

    public void noEscape(){
        Object noEscape = new Object();   // 仅创建线程可见，对象无逃逸
    }


    /**
     * 逃逸分析测试程序
     * @param args
     */
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            allot();
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
    }

    private static void allot() {
        Jet jet = new Jet();
    }

    static class Jet {
        public String name;
    }
}