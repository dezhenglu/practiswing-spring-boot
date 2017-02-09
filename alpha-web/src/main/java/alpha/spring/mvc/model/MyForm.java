package alpha.spring.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class MyForm {

    @NotEmpty
    String text;

    Email email;

    long number;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
