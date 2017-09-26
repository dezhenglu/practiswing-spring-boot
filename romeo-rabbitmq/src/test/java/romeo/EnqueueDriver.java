package romeo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class EnqueueDriver {
    // SMS送信リクエストIDをキューに登録するドライバ
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class)) {
            AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);

            amqpTemplate.convertAndSend("2");
        }
    }

    @Configuration
    public static class Config {

        private String queueName = "romeo.in";

        @Bean
        public ConnectionFactory connectionFactory() {
            CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            return connectionFactory;
        }

        @Bean
        public RabbitTemplate rabbitTemplate() {
            RabbitTemplate template = new RabbitTemplate(connectionFactory());
            template.setRoutingKey(queueName);
            template.setQueue(queueName);
            return template;
        }
    }
}
