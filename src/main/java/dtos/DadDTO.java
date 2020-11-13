package dtos;

import jokeEntities.DadJoke;

/**
 *
 * @author claes
 */
public class DadDTO {
    
    private String url = "https://icanhazdadjoke.com";
    private String joke;
    private DadJoke dj;


    public DadDTO(DadJoke dj, String url) {
        this.url = url;
        this.joke = dj.getJoke();
    }

    public DadDTO() {
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    
    
    
    
    
    
}
