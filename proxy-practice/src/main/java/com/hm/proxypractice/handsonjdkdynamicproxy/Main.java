package com.hm.proxypractice.handsonjdkdynamicproxy;

import com.hm.proxypractice.handsonjdkdynamicproxy.dynamic.HmInterfaceFactory;

import java.lang.reflect.Field;

/**
 * @author liuyanoutsee@outlook.com
 **/
public class Main {

    public static void main(String[] args) throws Exception {
        HmInterface proxyObject = HmInterfaceFactory.createProxyObject(new PrintFunctionName());
        proxyObject.func1();
        proxyObject.func2();
        proxyObject.func3();
        System.out.println("---------");
        proxyObject = HmInterfaceFactory.createProxyObject(new PrintFunctionName1());
        proxyObject.func1();
        proxyObject.func2();
        proxyObject.func3();
        System.out.println("---------");
        // 为什么还要setProxy？因为尽管动态代理工厂在内存中生成了java文件，并编译成了class文件给到jvm, 但class中的自定义属性还为赋值，是空的。在运行时反射赋值，这就是反射的价值！
        // 为什么叫setProxy？因为给代理对象的属性赋值操作用的是反射，所以命名有反过来的意味
        proxyObject = HmInterfaceFactory.createProxyObject(new LogHandler(proxyObject));
        proxyObject.func1();
        proxyObject.func2();
        proxyObject.func3();
    }

    /**
     * 用户自定义的Handler
     */
    static class PrintFunctionName implements HmHandler {

        @Override
        public String functionBody(String methodName) {
            return " System.out.println(\"" + methodName + "\");";
        }
    }

    /**
     * 用户自定义的Handler
     */
    static class PrintFunctionName1 implements HmHandler {

        @Override
        public String functionBody(String methodName) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" System.out.println(1);")
                    .append(" System.out.println(\"" + methodName + "\");");
            return stringBuilder.toString();
        }
    }

    /**
     * 用户自定义的Handler
     */
    static class LogHandler implements HmHandler {

        HmInterface hmInterface;

        public LogHandler(HmInterface hmInterface) {
            this.hmInterface = hmInterface;
        }

        @Override
        public void setProxy(HmInterface proxy) {
            Class<? extends HmInterface> aClass = proxy.getClass();
            Field field = null;
            try {
                // 为什么还要setProxy？因为尽管动态代理工厂在内存中生成了java文件，并编译成了class文件给到jvm, 但class中的自定义属性还为赋值，是空的。在运行时反射赋值，这就是反射的价值！
                // 为什么叫setProxy？因为给代理对象的属性赋值操作用的是反射，所以命名有反过来的意味
                field = aClass.getDeclaredField("hmInterface");
                field.setAccessible(true);
                // 虽然编译后的class文件中有这个属性，但是默认并没有赋值，所以这里通过反射手动赋值
                field.set(proxy, hmInterface);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        /**
         * 同时修改了func1、func2、func3的实现，把hmInterface的引用加进去：
         * hmInterface.func1();
         * hmInterface.func2();
         * hmInterface.func3();
         * @param methodName
         * @return
         */
        @Override
        public String functionBody(String methodName) {
            String context = "  System.out.println(\"before\");\n" +
                    "        hmInterface." + methodName + "();\n" +
                    "        System.out.println(\"after\");";
            return context;
        }
    }
}
