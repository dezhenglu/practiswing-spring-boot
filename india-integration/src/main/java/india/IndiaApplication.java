package india;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.dsl.IntegrationFlow;

@Configuration
@SpringBootApplication
@IntegrationComponentScan
public class IndiaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IndiaApplication.class);

        TempConverter converter = context.getBean(TempConverter.class);
        String celcius = converter.fahrenhitToCelcius(68.0f);
        System.out.println(celcius);
        context.close();
    }

    @Bean
    public IntegrationFlow convert() {

        return f -> f.transform(payload -> 100 + payload.toString() + "x");
    }
}
