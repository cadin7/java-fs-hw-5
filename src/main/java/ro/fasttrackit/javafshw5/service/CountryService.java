package ro.fasttrackit.javafshw5.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.javafshw5.model.Country;
import ro.fasttrackit.javafshw5.reader.CountryReader;
import ro.fasttrackit.javafshw5.repository.CountryRepository;

import java.util.*;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryReader countryReader;
    private final CountryRepository countries;
    private final CountryContext context;

    public List<Country> getCountries(String includedNeighbourCode, String excludedNeighbourCode) {
        return includedNeighbourCode != null
                && excludedNeighbourCode != null
                ? getCountriesByNeighbours(includedNeighbourCode, excludedNeighbourCode)
                : new ArrayList<>(getCountries());
    }

    public List<String> getCountryNames() {
        return getCountries().stream()
                .map(Country::getName)
                .toList();
    }

    public Optional<String> getCapital(Long countryId) {
        return getFilteredCountry(countryId)
                .map(Country::getCapital);
    }

    public Optional<Long> getPopulation(Long countryId) {
        return getFilteredCountry(countryId)
                .map(Country::getPopulation);
    }

    private List<Country> getCountriesBy(String continentName) {
        return getCountries().stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continentName))
                .toList();
    }

    public Optional<List<String>> getNeighbours(Long countryId) {
        return getFilteredCountry(countryId)
                .map(Country::getNeighbours);
    }

    public List<Country> getCountries(String continentName, Long minPopulation) {
        return minPopulation == null
                ? getCountriesBy(continentName)
                : getCountriesBy(continentName, minPopulation);
    }

    public Map<String, Long> getPopulationByCountry() {
        return getCountries().stream()
                .collect(toMap(
                        Country::getName,
                        Country::getPopulation,
                        Math::addExact,
                        TreeMap::new));
    }

    public Map<String, List<Country>> getCountriesByContinent() {
        return getCountries().stream()
                .collect(groupingBy(Country::getContinent,
                        TreeMap::new,
                        toUnmodifiableList()));
    }

    public Optional<Country> getMyCountry() {
        return getCountries().stream()
                .filter(country -> country.getName().equalsIgnoreCase(context.getMyCountryName()))
                .findFirst();
    }

    @PostConstruct
    private void saveCountries() {
        countries.saveAll(countryReader.readCountries());
    }

    private List<Country> getCountries() {
        return unmodifiableList(countries.findAll());
    }

    private List<Country> getCountriesByNeighbours(String includedNeighbourCode, String excludedNeighbourCode) {
        return getCountries().stream()
                .filter(country -> isNeighbour(includedNeighbourCode, country)
                        && !isNeighbour(excludedNeighbourCode, country))
                .toList();
    }

    private boolean isNeighbour(String includedNeighbourCode, Country country) {
        return country.getNeighbours().contains(includedNeighbourCode);
    }

    private List<Country> getCountriesBy(String continentName, Long minPopulation) {
        return getCountriesBy(continentName).stream()
                .filter(country -> country.getPopulation() > minPopulation)
                .toList();
    }

    private Optional<Country> getFilteredCountry(long countryId) {
        return getCountries().stream()
                .filter(country -> country.getId() == countryId)
                .findFirst();
    }
}
