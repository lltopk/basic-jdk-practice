package com.lyflexi.reflectpractice.field;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.Objects;
/**
 * @Author: lyflexi
 * @project: debuginfo_jdkToFramework
 * @Date: 2024/8/4 9:53
 */
@Slf4j
public class VOUtils {


//    private static final Logger logger = LoggerFactory.getLogger(VOUtils.class);

    /**
     * 将o1.id属性复制到o2.id
     *
     * 反射在这里用于审视类型内部信息(Object o1, Object o2)
     * 仅仅靠实例对象(明确的类型), 没有动态对象(未知对象类型)解析能力
     */
    private static void copyId (Object o1, Object o2) {
        if (Objects.isNull(o1) || Objects.isNull(o2)) {
            return;
        }
        try {
            // 提取o1.id属性值和类型
            //方式1: 完全通过反射审视o1
            Field field1 = reflectInspectField(o1);
            //方式2: 通过instanceof实例判断o1,但仅仅靠实例对象(明确的类型), 没有动态对象解析能力. 但由于还需要返回id的类型, 所以最终还是用了一半反射. 比较麻烦
//            Pair<Object, Class<?>> pair1= instanceInspectId(o1);
//            if(Objects.isNull(pair1)) {
//                return;
//            }
//            Object id = pair1.getKey();
//            Class<?> fieldCls1 = pair1.getValue();
            if(Objects.isNull(field1)) {
                return;
            }
            Object id = field1.get(o1);
            Class<?> fieldCls1 = field1.getType();

            // 提取o2.id属性和类型
            Field field2 = reflectInspectField(o2);
            if(Objects.isNull(field2)) {
                return;
            }
            Class<?> fieldCls2 = field2.getType();

            // 根据类型转换id并赋值给o2
            if (fieldCls1.equals(fieldCls2)) {
                return;
            }
            if (fieldCls2 == String.class) {
                field2.set(o2, String.valueOf(id));
            } else if (fieldCls2 == Long.class) {
                field2.set(o2, Long.parseLong(String.valueOf(id)));
            } else if (fieldCls2 == Integer.class) {
                field2.set(o2, Integer.parseInt(String.valueOf(id)));
            }
        } catch (Exception e) {
            log.error("对象id属性赋值错误："+ e);
            throw new RuntimeException("对象ID属性不正确，" + e.getMessage());
        }
    }

    /**
     * 通过实例instanceof审视内部, 返回(id值, id类型)对
     *
     * warning: 仅仅靠实例对象(确切的类型)是无法解析动态对象的内部信息.  因此程序中不推荐使用instanceof
     * @param o1
     * @return
     */
    @Deprecated
    private static Pair<Object, Class<?>> instanceofInspectId(Object o1) {
        if (o1 instanceof BasePo) {//用instanceof, 是无法动态的拿到类型的内部信息
            BasePo basePo = (BasePo) o1;
            Long id = basePo.getId();
            return Pair.of(id, BasePo.class);
        }

        //c1不是BasePo, 则自身必带有id, 但又不确定自身具体是什么类, 这个时候就需要反射动态的拿到类型的内部信息id
        //所以下面这句代码就安全了
        Class<?> c1 = o1.getClass();
        Field field = safeGetIdField(c1);
        if (Objects.isNull(field)) {
            return null;
        }
        field.setAccessible(true);
        Class<?> fieldCls = field.getType();
        Object id = null;
        try {
            id = field.get(o1);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Pair.of(id, fieldCls);
    }

    /**
     * 保证能得到id的Field
     * @param c
     * @return
     */
    private static Field safeGetIdField(Class<?> c) {
        try {
            return c.getDeclaredField("id");
        } catch (Exception e) {
            log.error("不会走到这...");
            return null;
        }
    }

    /**
     * 通过反射审视内部, 返回(id值, id类型)对
     * @param o
     * @return
     */
    private static Field reflectInspectField(Object o) {
        Class<?> c = o.getClass();
        //Object没有superClass
        Class<?> cc = c;//!!!, 用cc遍历, 把c当作缓存
        while (Objects.nonNull(cc.getSuperclass())) {
            //如果id在父类BasePo中, 由于反射无法隐式拿到父类, 必须明确指定cc为BasePo.class
            if (cc == BasePo.class) {
                c = cc;
                break;
            }
            cc = cc.getSuperclass();
        }

        //走到这, 说明c1要么是BasePo.class, 要么不是BasePo.class但本身就有id
        //所以下面这句代码就安全了
        Field field = safeGetIdField(c);
        if (Objects.isNull(field)) {
            return null;
        }
        field.setAccessible(true);
        return field;
    }


    public static <T> T convert(Object source, Class<T> targetClass) {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            copyId(source, target); // 调用copyId方法来复制id字段
            return target;
        } catch (Exception e) {
            log.error("对象转换时发生错误：{}",e.getMessage());
            throw new RuntimeException("对象转换失败：" + e.getMessage());
        }
    }


}
