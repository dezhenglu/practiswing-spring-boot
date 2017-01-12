package alfa;

import alfa.domain.Account;
import alfa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * @author irof
 */
@SpringBootApplication
public class AlfaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlfaApplication.class, args);
    }

    @Autowired
    AccountService service;

    @EventListener(ContextRefreshedEvent.class)
    void registerTestUser() {
        service.register(Account.builder()
                .username("user")
                .password("password")
                .mailAddress("user@hogedriven.net")
                .build());
    }
}
