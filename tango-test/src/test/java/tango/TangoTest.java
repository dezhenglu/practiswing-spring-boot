package tango;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TangoTest {

    @Autowired
    Object hoge;

    @Test
    public void test() throws Exception {
        assertThat(hoge).hasToString("hoge1");
    }

    @Configuration
    static class Config {
        @Bean("hoge")
        Object hoge1() {
            return "hoge1";
        }

        @Bean("hoge")
        Object hoge2() {
            return "hoge2";
        }
    }
}
