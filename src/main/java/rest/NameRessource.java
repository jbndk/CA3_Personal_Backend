package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CombinedNamesDTO;
import dtos.CountryDTO;
import dtos.GenderDTO;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import nameFetcher.NameFetcher;
import static rest.DemoResource.COUNTRY_SERVER;
import static rest.DemoResource.GENDER_SERVER;
import utils.HttpUtils;


@Path("name")
public class NameRessource {
    
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService es = Executors.newCachedThreadPool();
    
    String GENDER_SERVER = "https://api.genderize.io/?name=";
    String COUNTRY_SERVER = "https://api.nationalize.io/?name=";
    
    @Path("/{input}")
    @GET
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getName(@PathParam("input") String input) throws IOException, InterruptedException, ExecutionException, TimeoutException {

        String GENDER_URL = GENDER_SERVER + input;
        String COUNTRY_URL = COUNTRY_SERVER + input;
        
        String gender = HttpUtils.fetchData(GENDER_URL);
        GenderDTO genderDTO = gson.fromJson(gender, GenderDTO.class);

        String country = HttpUtils.fetchData(COUNTRY_URL);
        CountryDTO countryDTO = gson.fromJson(country, CountryDTO.class);

        CombinedNamesDTO combinedDTO = new CombinedNamesDTO(genderDTO, countryDTO);
       
       String combinedJSON = gson.toJson(combinedDTO);
       
       return combinedJSON;
    }
    
    @Path("/parallel")
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public String getNameParallel() throws IOException, InterruptedException, ExecutionException, TimeoutException {
       String result = NameFetcher.responseParallel(es, gson);
       return result;
    }
    
}