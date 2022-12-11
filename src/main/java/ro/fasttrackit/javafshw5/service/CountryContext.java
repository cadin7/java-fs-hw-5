package ro.fasttrackit.javafshw5.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ro.fasttrackit.javafshw5.exceptions.HeaderNotFoundException;

import static java.util.Optional.ofNullable;

@Getter
@Component
@RequestScope
public class CountryContext {
    private static final String X_COUNTRY = "X-Country";
    private static final String X_COUNTRY_ERROR = "Header named %s was not found!";

    private final String myCountryName;

    public CountryContext(HttpServletRequest request) {
        this.myCountryName = ofNullable(request.getHeader(X_COUNTRY))
                .orElseThrow(() -> new HeaderNotFoundException(String.format(X_COUNTRY_ERROR, X_COUNTRY)));
    }
}
