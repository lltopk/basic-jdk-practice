package com.hm.proxypractice.handsonjdkdynamicproxy.dynamic;


import com.hm.proxypractice.handsonjdkdynamicproxy.HmHandler;
import com.hm.proxypractice.handsonjdkdynamicproxy.HmInterface;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuyanoutsee@outlook.com
 *
 * 动态代理工厂，根据用户传入进来的接口，以及生成逻辑Handler：
 * 1. 动态的创建java文件
 * 2. 编译java文件，为class给到jvm
 * 3. 通过反射创建对象给到用户
 **/
public class HmInterfaceFactory {

    private static final AtomicInteger count = new AtomicInteger();

    private static File createJavaFile(String className, HmHandler handler) throws IOException {
        String func1Body = handler.functionBody("func1");
        String func2Body = handler.functionBody("func2");
        String func3Body = handler.functionBody("func3");
        String context = "package com.hm.proxypractice.handsonjdkdynamicproxy;\n" +
                "\n" +
                "public class " + className + " implements HmInterface {\n" +
                " HmInterface hmInterface;\n" +
                "    @Override\n" +
                "    public void func1() {\n" +
                "       " + func1Body + "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func2() {\n" +
                "       " + func2Body + "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func3() {\n" +
                "       " + func3Body + "\n" +
                "    }\n" +
                "}\n";
        File javaFile = new File(className + ".java");
        Files.writeString(javaFile.toPath(), context);
        return javaFile;
    }

    /**
     * 生成的Java对象名字是动态的，格式是："HmInterface$proxy" + count.incrementAndGet();
     * @return
     */
    private static String getClassName() {
        return "HmInterface$proxy" + count.incrementAndGet();
    }


    /**
     * 反射创建出对象给到用户
     * @param className
     * @param handler 用户传入的handler自定义逻辑
     * @return
     * @throws Exception
     */
    private static HmInterface newInstance(String className, HmHandler handler) throws Exception {
        //1. 通过类加载器加载类
        Class<?> aClass = HmInterfaceFactory.class.getClassLoader().loadClass(className);
        Constructor<?> constructor = aClass.getConstructor();
        //2. 通过反射创建对象
        HmInterface proxy = (HmInterface) constructor.newInstance();
        //3. 此时虽然已经有了内存中生成的java文件，和编译后给到jvm的class文件。但其中的自定义属性还是空为，尚未被赋值。
        // 执行用户自定义的setProxy方法是通过反射赋值，这就是反射的价值，运行时！！
        handler.setProxy(proxy);
        return proxy;
    }

    /**
     * 对外暴露的api
     * @param hmHandler  用户传入的handler自定义逻辑
     * @return
     * @throws Exception
     */
    public static HmInterface createProxyObject(HmHandler hmHandler) throws Exception {
        String className = getClassName();
        File javaFile = createJavaFile(className, hmHandler);
        Compiler.compile(javaFile);
        return newInstance("com.hm.proxypractice.handsonjdkdynamicproxy." + className, hmHandler);
    }

}
