package com.lyflexi.reflectpractice.field;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/13 20:11
 * @description
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        UserPo userPo = new UserPo();
        userPo.setId(0L);
        userPo.setAge(18);
        UserVo convert = VOUtils.convert(userPo, UserVo.class);
        log.error("转换结果：{}", convert);
        log.error("转换结果：{}", convert.getId().getClass());
    }
}
