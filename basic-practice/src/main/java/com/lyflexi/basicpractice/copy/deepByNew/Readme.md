# 深拷贝deepCopy

深拷贝是将一个对象从内存中完整的复制一份出来，从堆内存中开辟一个新的区域存放新对象，且修改新对象属性值不会影响原对象

## 使用New即可解决

如上述翠山的例子：

```java

    /**
     * case3： 深拷贝，son重新创建新的life
     */
    private static void testDeep(){
        Father cuishan = new Father();
        cuishan.setFace("handsome");
        cuishan.setHeight("180");
        Life cuishanLife = new Life();
        cuishanLife.setStatus("alive");
        cuishan.setLife(cuishanLife);
        Son wuji=new Son();
        BeanUtils.copyProperties(cuishan,wuji);

        cuishanLife.setStatus("dead"); // 翠山自刎了  该行放在上下均可
        // 无忌用个新对象 不受翠山影响了
        Life wujiLife = new Life();
        wujiLife.setStatus("alive");
        wuji.setLife(wujiLife);


        System.out.println(JSON.toJSONString(cuishan));
        System.out.println(JSON.toJSONString(wuji));
    }
```