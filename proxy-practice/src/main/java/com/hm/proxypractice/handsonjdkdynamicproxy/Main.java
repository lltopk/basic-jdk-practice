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
        //为什么叫setProxy？因为给Proxy对象设置额外的属性，这个额外的属性甚至是代理后的HmInterface
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

        //为什么叫setProxy？因为给Proxy对象设置额外的属性，这个额外的属性是代理前的HmInterface
        @Override
        public void setProxy(HmInterface proxy) {
            Class<? extends HmInterface> aClass = proxy.getClass();
            Field field = null;
            try {
                //为什么叫setProxy？因为给Proxy对象设置额外的属性，这个额外的属性甚至是代理后的HmInterface
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
