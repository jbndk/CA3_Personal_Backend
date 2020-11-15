package NameEntities;

public class Gender {
    
    private final String name;
    private final String gender;
    private final String probability;

    public Gender(String name, String gender, String probability) {
        this.name = name;
        this.gender = gender;
        this.probability = probability;
    }

    public String getGender() {
        return gender;
    }
    
    public String getName() {
        return name;
    }
    
            public String getProbability() {
        return probability;
    }

}
