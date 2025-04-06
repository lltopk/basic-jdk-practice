package com.lyflexi.collectionpractice.handsonhashmap;

import com.lyflexi.collectionpractice.handsonhashmap.v1.HmHashMapV1;
import com.lyflexi.collectionpractice.handsonhashmap.v2.HmHashMapV2;
import com.lyflexi.collectionpractice.handsonhashmap.v3.HmHashMapV3;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class HmHashMapTest {


    @Test
    void testApi() {
        HmHashMapV1<String, String> hmHashMap = new HmHashMapV1<>();//无穷大秒
//        HmHashMapV2<String, String> hmHashMap = new HmHashMapV2<>();//十万元素测试，18s
//        HmHashMapV3<String, String> hmHashMap = new HmHashMapV3<>();//十万元素测试，530ms
        int count = 100000;
        for (int i = 0; i < count; i++) {
            hmHashMap.put(String.valueOf(i), String.valueOf(i));
        }

        assertEquals(count, hmHashMap.size());

        for (int i = 0; i < count; i++) {
            assertEquals(String.valueOf(i), hmHashMap.get(String.valueOf(i)));
        }

        hmHashMap.remove("8");
        assertNull(hmHashMap.get("8"));

        assertEquals(count - 1, hmHashMap.size());

    }

}
