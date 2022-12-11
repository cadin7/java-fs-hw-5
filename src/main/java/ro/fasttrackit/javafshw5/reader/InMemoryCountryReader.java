package ro.fasttrackit.javafshw5.reader;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.fasttrackit.javafshw5.model.Country;

import java.util.List;

@Component
@Profile("memory")
public class InMemoryCountryReader implements CountryReader {
    @Override
    public List<Country> readCountries() {
        return List.of(
                Country.builder()
                        .name("Brazil")
                        .capital("Brasília")
                        .population(206135893L)
                        .area(8515767L)
                        .continent("Americas")
                        .neighbours(List.of("ARG", "BOL", "COL", "GUF", "GUY", "PRY", "PER", "SUR", "URY", "VEN"))
                        .build(),
                Country.builder()
                        .name("Burkina Faso")
                        .capital("Ouagadougou")
                        .population(19034397L)
                        .area(272967L)
                        .continent("Africa")
                        .neighbours(List.of("BEN", "CIV", "GHA", "MLI", "NER", "TGO"))
                        .build(),
                Country.builder()
                        .name("Mongolia")
                        .capital("Ulan Bator")
                        .population(3093100L)
                        .area(1564110L)
                        .continent("Asia")
                        .neighbours(List.of("CHN", "RUS"))
                        .build(),
                Country.builder()
                        .name("Nicaragua")
                        .capital("Managua")
                        .population(6262703L)
                        .area(130373L)
                        .continent("Americas")
                        .neighbours(List.of("CRI", "HND"))
                        .build(),
                Country.builder()
                        .name("Romania")
                        .capital("Bucharest")
                        .population(19861408L)
                        .area(238391L)
                        .continent("Europe")
                        .neighbours(List.of("BGR", "HUN", "MDA", "SRB", "UKR"))
                        .build(),
                Country.builder()
                        .name("Hungary")
                        .capital("Budapest")
                        .population(9500000L)
                        .area(138391L)
                        .continent("Europe")
                        .neighbours(List.of("AUS", "HUN", "CRO", "SRB", "UKR", "SLO"))
                        .build()
        );
    }
}
