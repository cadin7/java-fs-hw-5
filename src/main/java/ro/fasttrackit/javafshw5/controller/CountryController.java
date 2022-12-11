package ro.fasttrackit.javafshw5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.javafshw5.exceptions.CountryNotFoundException;
import ro.fasttrackit.javafshw5.model.Country;
import ro.fasttrackit.javafshw5.service.CountryService;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@RestController
@RequiredArgsConstructor
@RequestMapping("countries")
public class CountryController {
    private static final String NO_COUNTRY_ID = "There is no country with id: %s ";

    private final CountryService service;

    @GetMapping
    List<Country> getCountries(@RequestParam(required = false) String includedNeighbourCode,
                               @RequestParam(required = false) String excludedNeighbourCode) {
        return service.getCountries(includedNeighbourCode, excludedNeighbourCode);
    }

    @GetMapping("names")
    List<String> getCountryNames() {
        return service.getCountryNames();
    }

    @GetMapping("{countryId}/capital")
    String getCapital(@PathVariable Long countryId) {
        return service.getCapital(countryId)
                .orElseThrow(() -> new CountryNotFoundException(format(NO_COUNTRY_ID, countryId)));
    }

    @GetMapping("{countryId}/population")
    long getPopulation(@PathVariable Long countryId) {
        return service.getPopulation(countryId)
                .orElseThrow(() -> new CountryNotFoundException(format(NO_COUNTRY_ID, countryId)));
    }

    @GetMapping("{countryId}/neighbours")
    List<String> getNeighbours(@PathVariable Long countryId) {
        return service.getNeighbours(countryId)
                .orElseThrow(() -> new CountryNotFoundException(format(NO_COUNTRY_ID, countryId)));
    }

    @GetMapping("population")
    Map<String, Long> getPopulationByCountry() {
        return service.getPopulationByCountry();
    }

    @GetMapping("mine")
    Country getMyCountry() {
        return service.getMyCountry();
    }
}
