package com.lyflexi.genericpractice.grammar;

/**
 * @Description:
 * @Author: lyflexi
 * @project: jdk-practice
 * @Date: 2024/9/15 16:09
 */

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ApiSample {

    private static final Logger logger = LoggerFactory.getLogger(ApiSample.class);

    // 使用自定义类作为 T 的类型
    static class CustomClass {
        public int x;
        public int y;

        public CustomClass(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public CustomClass(int x, int y, int z) {
            this.x = x;
            this.y = y;
        }

        public void printXY() {
            logger.info("X: {}, Y: {}", x, y);
        }
    }

    /**
     * 来自于jdk17：Class<T>类注释
     * Type parameters:
     * <T> – the type of the class modeled by this Class object.
     * For example, the type of String. class is Class<String>.
     * Use Class<?> if the class being modeled is unknown.
     *
     * @param clazz
     * @param <T>
     */
    private <T> void useClass(Class<T> clazz) {
        // 获取类名
        logger.info("Full name: {}", clazz.getName());
        logger.info("Simple name: {}", clazz.getSimpleName());

        // 获取直接超类
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            logger.info("Superclass0: {}", superClass.getName());
        } else {
            logger.info("This is the top-level class.");
        }

        //循环获取超类, 要创建本地变量current进行迭代, 避免修改clazz本身
        Class<?> current = clazz.getSuperclass();
        int i = 1;
        while (current != null) {
            logger.info("Superclass i {}: {}", i++, current.getName());
            current = current.getSuperclass();
        }

        // 获取实现的接口
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            logger.info("Implemented interfaces: {}", java.util.Arrays.toString(interfaces));
        }

        // 获取构造函数
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length > 0) {
            logger.info("Declared constructors: {}", java.util.Arrays.toString(constructors));
        }

        // 获取方法
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length > 0) {
            logger.info("Declared methods: {}", java.util.Arrays.toString(methods));
        }

        // 获取字段
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length > 0) {
            logger.info("Declared fields: {}", java.util.Arrays.toString(fields));
        }

        // 获取注解
        if (clazz.isAnnotationPresent(Deprecated.class)) {
            logger.info("The class is marked as @Deprecated.");
        } else {
            logger.info("The class is not marked as @Deprecated.");
        }
    }


    private <T> T initTarget(Class<T> clazz){

        // 获取构造函数, 默认是无参构造, 当有参构造的时候获取的是有参构造
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length > 0) {
            logger.info("Declared constructors: {}", java.util.Arrays.toString(constructors));
        }
        T rs;
        try {
            // 检查构造函数的参数
            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount() == 2 && constructor.getParameterTypes()[0] == int.class && constructor.getParameterTypes()[1] == int.class) {
                    rs = (T) constructor.newInstance(1, 2); // 使用默认参数
                    try {
                        Field x = clazz.getField("x");
                        Field y = clazz.getField("y");
                        x.set(rs, (int)x.get(rs)*2);
                        y.set(rs, (int)y.get(rs)*2);
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    return rs;
                }
            }
            // 如果没有找到合适的构造函数，使用无参构造函数
            rs = clazz.getDeclaredConstructor().newInstance();
            Field x = null;
            try {
                x = clazz.getField("x");
                Field y = clazz.getField("y");
                x.set(rs, 1);
                y.set(rs, 2);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public static void main(String[] args) {
        ApiSample apiSample = new ApiSample();

        // 使用 Integer 类作为 T 的类型
        apiSample.useClass(Integer.class);
        // 使用 String 类作为 T 的类型
        apiSample.useClass(String.class);

        // 使用 自定义 类作为 T 的类型, 并生成实例返回
        CustomClass customClass = apiSample.initTarget(CustomClass.class);
        customClass.printXY();
    }
}