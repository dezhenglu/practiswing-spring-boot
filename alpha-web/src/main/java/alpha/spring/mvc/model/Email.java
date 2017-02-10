package alpha.spring.mvc.model;


import org.springframework.beans.TypeMismatchException;

public class Email {

    private final String value;

    public Email(String value) {
        if (!value.matches("\\w+@\\w+")) {
            throw new TypeMismatchException(value, Email.class);
        }
        this.value = value;
    }

    @Override
    public String toString() {
        // なんか値を編集する
        return "<" + value + ">";
    }
}
