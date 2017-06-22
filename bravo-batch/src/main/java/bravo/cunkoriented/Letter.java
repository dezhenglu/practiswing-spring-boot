package bravo.cunkoriented;

public class Letter {

    final String value;

    public Letter(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "value='" + value + '\'' +
                '}';
    }
}
