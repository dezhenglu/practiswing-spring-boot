package alpha;

import alpha.domain.Account;
import alpha.service.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.stream.Stream;

/**
 * @author irof
 */
@SpringBootApplication
public class AlphaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AlphaApplication.class, args);

        AccountService service = context.getBean(AccountService.class);

        Stream.of(
                "alpha", "bravo", "charlie", "delta", "echo",
                "foxtrot", "golf", "hotel", "india", "juliet",
                "kilo", "lima", "mike").forEach(v -> {
            service.register(Account.builder()
                    .username(v)
                    .password("password")
                    .mailAddress(v + "@hogedriven.net")
                    .build());
        });
    }
}
