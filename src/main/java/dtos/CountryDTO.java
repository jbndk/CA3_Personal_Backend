package dtos;

import NameEntities.Country;
import java.util.ArrayList;
import java.util.List;

public class CountryDTO {

    private String name;
    private List<Country> country;

    public CountryDTO(String name, ArrayList<Country> country) {
        this.name = name;
        this.country = country;
    }

    public CountryDTO() {
    }

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}