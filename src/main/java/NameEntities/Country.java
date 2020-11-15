package NameEntities;

import java.util.ArrayList;
import java.util.List;

public class Country {
    
    private String country_id;
    private String probability;
    
    public Country(String country_id, String probability) {
        this.country_id = country_id;
        this.probability = probability;
    }

        public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }
    
    public Country() {
    }
    

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

}