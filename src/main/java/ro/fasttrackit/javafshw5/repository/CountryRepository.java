package ro.fasttrackit.javafshw5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.javafshw5.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
