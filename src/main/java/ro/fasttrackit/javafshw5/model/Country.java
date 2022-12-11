package ro.fasttrackit.javafshw5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String capital;

    private long population;

    private long area;

    private String continent;

    @ElementCollection
    private List<String> neighbours;

    public Country(String name, String capital, long population, long area, String continent, List<String> neighbours) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = neighbours;
    }
}