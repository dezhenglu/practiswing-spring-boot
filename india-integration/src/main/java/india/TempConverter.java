package india;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface TempConverter {

    @Gateway(requestChannel = "convert.input")
    String fahrenhitToCelcius(float fahren);
}
