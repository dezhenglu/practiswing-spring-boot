package tango;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:specific.properties")
public class TestPropertySourcePropertyTest {

    @Value("${tango.hoge}")
    String hoge;

    @Value("${tango.fuga}")
    String fuga;

    @Test
    public void hoge() throws Exception {
        assertThat(hoge).isEqualTo("hoge");
    }

    @Test
    public void fuga() throws Exception {
        assertThat(fuga).isEqualTo("fuga-test");
    }
}
