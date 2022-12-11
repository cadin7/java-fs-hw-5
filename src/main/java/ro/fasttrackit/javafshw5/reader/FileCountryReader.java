package ro.fasttrackit.javafshw5.reader;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.fasttrackit.javafshw5.config.CountryFileConfig;
import ro.fasttrackit.javafshw5.exceptions.FileReadException;
import ro.fasttrackit.javafshw5.model.Country;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.lang.Long.parseLong;
import static java.nio.file.Files.lines;
import static java.util.stream.Stream.of;

@Component
@Profile("file")
public class FileCountryReader implements CountryReader {
    private final CountryFileConfig countryFileConfig;

    public FileCountryReader(CountryFileConfig countryFileConfig) {
        this.countryFileConfig = countryFileConfig;
    }

    @Override
    public List<Country> readCountries() {
        try (var lines = lines(Path.of(countryFileConfig.getFilePath()))) {
            return lines.map(this::readCountry)
                    .toList();
        } catch (IOException e) {
            throw new FileReadException("Could not read from file: " + countryFileConfig.getFilePath());
        }
    }

    private Country readCountry(String line) {
        String[] countryInfo = line.split("\\|");
        return new Country(
                countryInfo[0],
                countryInfo[1],
                parseLong(countryInfo[2]),
                parseLong(countryInfo[3]),
                countryInfo[4],
                countryInfo.length > 5 ? parseNeighbours(countryInfo[5]) : List.of()
        );
    }

    private List<String> parseNeighbours(String neighbours) {
        return of(neighbours.split("~"))
                .toList();
    }
}
