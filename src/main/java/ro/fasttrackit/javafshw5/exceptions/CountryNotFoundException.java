package ro.fasttrackit.javafshw5.exceptions;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String message) {
        super(message);
    }
}
