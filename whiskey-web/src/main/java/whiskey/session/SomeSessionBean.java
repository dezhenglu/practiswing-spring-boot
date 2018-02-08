package whiskey.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@SessionScope
@Component
public class SomeSessionBean {

    private final UUID uuid;
    private String key;
    private String value;

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SomeSessionBean() {
        uuid = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "SomeSessionBean{" +
                "uuid=" + uuid +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
