package ro.fasttrackit.javafshw5.exceptions;

import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class CountryControllerAdvice {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(CountryNotFoundException.class)
    ApiError handleCountryNotFoundException(CountryNotFoundException cnfe) {
        return new ApiError(1001, cnfe.getMessage());
    }

    @ResponseStatus(NO_CONTENT)
    @ExceptionHandler(FileReadException.class)
    ApiError handleFileReadException(FileReadException fre) {
        return new ApiError(1002, fre.getMessage());
    }

    @ResponseStatus(EXPECTATION_FAILED)
    @ExceptionHandler({HeaderNotFoundException.class, MissingRequestHeaderException.class})
    ApiError handleHeaderNotFoundException(Exception e) {
        return new ApiError(2001, e.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    ApiError handleOtherException(Exception e) {
        return new ApiError(9999, "Generic error: " + e.getMessage());
    }
}

record ApiError(int code, String message) {
}
