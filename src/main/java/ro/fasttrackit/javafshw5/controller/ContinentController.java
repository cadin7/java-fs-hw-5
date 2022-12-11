package ro.fasttrackit.javafshw5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.javafshw5.model.Country;
import ro.fasttrackit.javafshw5.service.CountryService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("continents")
public class ContinentController {
    private final CountryService service;

    @GetMapping("{continentName}/countries")
    List<Country> getCountries(@PathVariable String continentName,
                               @RequestParam(required = false) Long minPopulation) {
        return service.getCountries(continentName, minPopulation);
    }

    @GetMapping("countries")
    Map<String, List<Country>> getCountries() {
        return service.getCountriesByContinent();
    }
}
