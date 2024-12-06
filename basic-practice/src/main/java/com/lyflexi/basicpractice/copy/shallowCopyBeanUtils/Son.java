package com.lyflexi.basicpractice.copy.shallowCopyBeanUtils;

import com.alibaba.fastjson2.JSON;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Description:
 * @Author: lyflexi
 * @project: basic-jdk-practice
 * @Date: 2024/12/6 9:51
 */
@Data
public class Son extends Father{
    private Life life;
}
