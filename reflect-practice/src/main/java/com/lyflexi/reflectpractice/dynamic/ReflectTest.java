package com.lyflexi.reflectpractice.dynamic;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 反射相较于静态编译，体现在运行时&&动态语言，赋予了Java更强大的灵活性和动态性。
 * @author: hmly
 * @date: 2025/8/2 22:35
 * @param:
 * @return:
 **/
@Slf4j
public class ReflectTest {
    public static void main(String[] args) {
//        defaultTest();
//        reflectFiled();
        reflectMethod();
    }


    /**
     * 默认情况下，Entity的name值为"default"。
     */
    private static  void defaultTest() {
        BizLogic1 bizLogic1 = new BizLogic1();
        log.info("Entity created: {}", bizLogic1);
    }

    /**
     * 使用反射获取和设置Entity类的字段值。
     * 反射允许在运行时访问类的私有字段和方法。
     */
    private static  void reflectFiled() {
        BizLogic1 bizLogic1 = new BizLogic1();
        log.info("Entity created: {}", bizLogic1);

        // 反射获取字段值
        try {
            java.lang.reflect.Field idField = bizLogic1.getClass().getDeclaredField("id");
            idField.setAccessible(true); // 允许访问私有字段
            Long idValue = (Long) idField.get(bizLogic1);
            log.info("ID field value: {}", idValue);

            java.lang.reflect.Field nameField = bizLogic1.getClass().getDeclaredField("name");
            nameField.setAccessible(true); // 允许访问私有字段
            String nameValue = (String) nameField.get(bizLogic1);
            log.info("Name field value: {}", nameValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Error accessing fields: ", e);
        }
        // 反射设置字段值
        try {
            java.lang.reflect.Field idField = bizLogic1.getClass().getDeclaredField("id");
            idField.setAccessible(true); // 允许访问私有字段
            idField.set(bizLogic1, 123L); // 设置ID值

            java.lang.reflect.Field nameField = bizLogic1.getClass().getDeclaredField("name");
            nameField.setAccessible(true); // 允许访问私有字段
            nameField.set(bizLogic1, "Updated Name"); // 设置Name值

            log.info("Entity after reflection update: {}", bizLogic1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Error setting fields: ", e);
        }
    }

    /**
     * 使用反射调用Entity类的私有方法。
     * 反射允许在运行时调用类的私有方法。
     */
    private static  void reflectMethod() {
        BizLogic1 bizLogic1 = new BizLogic1();
        log.info("Entity created: {}", bizLogic1);
        // 反射执行方法
        try {
            java.lang.reflect.Method setIdMethod = bizLogic1.getClass().getDeclaredMethod("setId", Long.class);
            setIdMethod.setAccessible(true); // 允许访问私有方法
            setIdMethod.invoke(bizLogic1, 456L); // 调用setId方法

            java.lang.reflect.Method setNameMethod = bizLogic1.getClass().getDeclaredMethod("setName", String.class);
            setNameMethod.setAccessible(true); // 允许访问私有方法
            setNameMethod.invoke(bizLogic1, "New Name"); // 调用setName方法

            log.info("Entity after reflection method update: {}", bizLogic1);
        } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            log.error("Error invoking methods: ", e);
        }
    }
}
