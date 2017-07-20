import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RabbitMQTest {

    @Test
    public void send() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");

        try (Connection conn = factory.newConnection()) {
            Channel channel = conn.createChannel();
            try (AutoCloseable ac = channel::close) {
                String QUEUE = "nonoqueue";

                channel.queueDeclare(QUEUE, false, false, false, Collections.emptyMap());
                byte[] body = ("HELLO_" + LocalTime.now()).getBytes();
                channel.basicPublish("", QUEUE, MessageProperties.BASIC, body);
            }
        }

        try (Connection conn = factory.newConnection()) {
            Channel channel = conn.createChannel();
            try (AutoCloseable ac = channel::close) {
                String QUEUE = "nonoqueue";

                channel.queueDeclare(QUEUE, false, false, false, Collections.emptyMap());

                // 1件は読む
                CountDownLatch latch = new CountDownLatch(1);
                channel.basicConsume(QUEUE, false, new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println(consumerTag);
                        System.out.println(envelope);
                        System.out.println(properties);
                        System.out.println("CONSUME: " + new String(body));

                        channel.basicAck(envelope.getDeliveryTag(), false);

                        latch.countDown();
                    }
                });

                latch.await(3, TimeUnit.SECONDS);
            }
        }
    }
}
