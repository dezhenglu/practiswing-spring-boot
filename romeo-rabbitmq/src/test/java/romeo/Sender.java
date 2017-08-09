package romeo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Collections;

public class Sender {

    @Test
    public void test() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");

        try (Connection conn = factory.newConnection()) {
            Channel channel = conn.createChannel();
            try (AutoCloseable ac = channel::close) {
                String QUEUE = "romeo.in";

                channel.queueDeclare(QUEUE, true, false, false, Collections.emptyMap());
                byte[] body = ("HELLO_" + LocalTime.now()).getBytes();
                channel.basicPublish("", QUEUE, MessageProperties.TEXT_PLAIN, body);
            }
        }
    }
}
