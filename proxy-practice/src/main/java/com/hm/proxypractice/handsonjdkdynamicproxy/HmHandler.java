package com.hm.proxypractice.handsonjdkdynamicproxy;

/**
 * @author liuyanoutsee@outlook.com
 *
 * 注意Handler与HmInterfaceFactory生成的代码无关，HmInterfaceFactory只负责生成HmInterface，最后由HmInterfaceFactory负责执行HmHandler而已
 *
 * - functionBody
 * - setProxy
 **/
public interface HmHandler {

    /**
     * @param methodName
     * @return
     */
    String functionBody(String methodName);

    //骚操作，二层代理
    //为什么叫setProxy？因为给Proxy对象设置额外的属性，这个额外的属性甚至是代理后的HmInterface
    default void setProxy(HmInterface proxy) {

    }
}
