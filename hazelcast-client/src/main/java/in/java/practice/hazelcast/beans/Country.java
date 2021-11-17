package in.java.practice.hazelcast.beans;

import java.io.Serializable;
import java.util.Objects;

public class Country implements Serializable {

    private String name;
    private String continent;

    public Country(){}

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return name.equals(country.name) && continent.equals(country.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, continent);
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                '}';
    }
}
