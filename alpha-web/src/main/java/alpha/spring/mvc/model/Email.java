package alpha.spring.mvc.model;

public class Email {

    private final String value;

    public Email(String value) {
        if (!value.matches("\\w+@\\w+")) {
            throw new IllegalArgumentException(value);
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
