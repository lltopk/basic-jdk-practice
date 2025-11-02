package test;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.hm.eippractice.test.Child;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/11/2 15:44
 * @description
 */
@Slf4j
public class AccessControlTest {
    @Test
    public void test() {
        Child child = new Child();
        child.init();
        child.publicMethod();
        log.info(child.getKey());
    }
}
