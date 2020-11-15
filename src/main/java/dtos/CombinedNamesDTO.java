package dtos;

import NameEntities.Country;
import NameEntities.Gender;
import java.util.ArrayList;
import java.util.List;

public class CombinedNamesDTO {
    
    private String name;
    private String gender;
    private String probability;
    private List<Country> countries = new ArrayList<>();

    public CombinedNamesDTO(GenderDTO genderDTO, CountryDTO countryDTO) {
        this.name = genderDTO.getName();
        this.gender = genderDTO.getGender();
        this.probability = genderDTO.getProbability();
        this.countries = countryDTO.getCountry();      
    }
    
    public CombinedNamesDTO (){ 
    }

    public CombinedNamesDTO(String gender, String country) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }    
    
    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }
}
