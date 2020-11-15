package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ChuckDTO;
import dtos.CombinedJokesDTO;
import dtos.DadDTO;
import jokeEntities.ChuckJoke;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import jokefetcher.JokeFetcher;
import nameFetcher.NameFetcher;
import utils.HttpUtils;


@Path("unused")
public class NameRessource {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService es = Executors.newCachedThreadPool();

    @Context
    private UriInfo context;
    
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public String getNameParralel() throws IOException, InterruptedException, ExecutionException, TimeoutException {
       String result = NameFetcher.responseParallel(es, gson);
       return result;
    }
    
    @GET
    @Path("sekvential")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokes() throws IOException {
        
        String chuck = HttpUtils.fetchData("https://api.genderize.io/?name=kim");
        ChuckDTO chuckDTO = gson.fromJson(chuck, ChuckDTO.class);
        
        String dad = HttpUtils.fetchData("https://api.nationalize.io/?name=kim");
        DadDTO dadDTO = gson.fromJson(dad, DadDTO.class);
        
        CombinedJokesDTO comb = new CombinedJokesDTO(dadDTO, chuckDTO);
        
        String combinedJSON = gson.toJson(comb);
        return combinedJSON;
        
       }
    
}