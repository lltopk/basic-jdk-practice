package com.lyflexi.reflectpractice.dynamic;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/8/2 23:11
 */
public class SampleStaticCompile {
    public static void main(String[] args) {
        processStaticCompile();
    }

    /**
     * 静态编译示例
     * 直接使用BizLogic1类进行编译和调用, 只能在编译时确定类型和方法。
     * 静态编译的好处是性能更高，因为编译器可以在编译时进行优化，
     * 但缺点是缺乏灵活性
     */
    private static void processStaticCompile() {
        BizLogic1 bizLogic1 = new BizLogic1(1L, "Static Compile Example");
        bizLogic1.doSomething("Executing static compile method");
    }
}
