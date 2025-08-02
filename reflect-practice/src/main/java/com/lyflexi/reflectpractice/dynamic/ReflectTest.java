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
        Entity entity = new Entity();
        log.info("Entity created: {}", entity);
    }

    /**
     * 使用反射获取和设置Entity类的字段值。
     * 反射允许在运行时访问类的私有字段和方法。
     */
    private static  void reflectFiled() {
        Entity entity = new Entity();
        log.info("Entity created: {}", entity);

        // 反射获取字段值
        try {
            java.lang.reflect.Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true); // 允许访问私有字段
            Long idValue = (Long) idField.get(entity);
            log.info("ID field value: {}", idValue);

            java.lang.reflect.Field nameField = entity.getClass().getDeclaredField("name");
            nameField.setAccessible(true); // 允许访问私有字段
            String nameValue = (String) nameField.get(entity);
            log.info("Name field value: {}", nameValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Error accessing fields: ", e);
        }
        // 反射设置字段值
        try {
            java.lang.reflect.Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true); // 允许访问私有字段
            idField.set(entity, 123L); // 设置ID值

            java.lang.reflect.Field nameField = entity.getClass().getDeclaredField("name");
            nameField.setAccessible(true); // 允许访问私有字段
            nameField.set(entity, "Updated Name"); // 设置Name值

            log.info("Entity after reflection update: {}", entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Error setting fields: ", e);
        }
    }

    /**
     * 使用反射调用Entity类的私有方法。
     * 反射允许在运行时调用类的私有方法。
     */
    private static  void reflectMethod() {
        Entity entity = new Entity();
        log.info("Entity created: {}", entity);
        // 反射执行方法
        try {
            java.lang.reflect.Method setIdMethod = entity.getClass().getDeclaredMethod("setId", Long.class);
            setIdMethod.setAccessible(true); // 允许访问私有方法
            setIdMethod.invoke(entity, 456L); // 调用setId方法

            java.lang.reflect.Method setNameMethod = entity.getClass().getDeclaredMethod("setName", String.class);
            setNameMethod.setAccessible(true); // 允许访问私有方法
            setNameMethod.invoke(entity, "New Name"); // 调用setName方法

            log.info("Entity after reflection method update: {}", entity);
        } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
            log.error("Error invoking methods: ", e);
        }
    }
}
