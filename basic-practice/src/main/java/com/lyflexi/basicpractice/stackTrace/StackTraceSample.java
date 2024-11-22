package com.lyflexi.basicpractice.stackTrace;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/11/22 14:55
 */
public class StackTraceSample {



    public static void main(String[] args) {
        StackTraceSample stackTraceSample = new StackTraceSample();
        // 调用者方法
        stackTraceSample.callerMethod();
    }
    @XxlJob("callerMethod")
    private  void callerMethod() {
        System.out.println("Caller method name: " + getCallerMethodName(2));
    }

    /**
     * 获取调用此方法的方法名，并从注解中提取值
     * @return 调用者的函数名称或注解中的值
     */
    private String getCallerMethodName(Integer level) {
        if (level<0){
            return "";
        }
        // 获取当前线程的堆栈跟踪
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        // 堆栈跟踪中的第1个元素是Thread.getStackTrace
        // 堆栈跟踪中的第2个元素是当前方法
        // 堆栈跟踪中的第3个元素是调用此方法的方法
        if (stackTraceElements.length > level) {
            StackTraceElement caller = stackTraceElements[level];
            String className = caller.getClassName();
            String methodName = caller.getMethodName();

            // 使用反射获取调用者的方法
            try {
                Class<?> clazz = Class.forName(className);

//                Method method = clazz.getMethod(methodName);

                //如果需要获取私有方法，可以使用反射的 getDeclaredMethod 方法，而不是 getMethod。
                // getDeclaredMethod 可以访问类的所有方法，包括私有方法、受保护方法和包私有方法。
                Method method = clazz.getDeclaredMethod(methodName);
                method.setAccessible(true);
                // 检查方法是否有 XxlJob 注解
                if (method.isAnnotationPresent(XxlJob.class)) {
                    XxlJob annotation = method.getAnnotation(XxlJob.class);
                    return annotation.value();
                }
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                // 处理异常情况
                e.printStackTrace();
            }
        }

        // 如果没有找到调用者或注解，则返回空字符串
        return "";
    }
}
