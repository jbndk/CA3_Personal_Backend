package dtos;

import jokeEntities.ChuckJoke;

/**
 *
 * @author claes
 */
public class ChuckDTO {
    
    private String value;
    private String url = "https://api.chucknorris.io/jokes/random";
    private ChuckJoke cj;

    public ChuckDTO(ChuckJoke cj) {
        this.value = cj.getValue();
        this.url = cj.getUrl();
    }

    public ChuckDTO() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ChuckJoke getCj() {
        return cj;
    }

    public void setCj(ChuckJoke cj) {
        this.cj = cj;
    }

    
    
    

    
    
    
    
}
