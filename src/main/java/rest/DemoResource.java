package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ChuckDTO;
import dtos.CombinedJokesDTO;
import dtos.CombinedNamesDTO;
import dtos.CountryDTO;
import dtos.DadDTO;
import dtos.GenderDTO;
import entities.User;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;
import utils.HttpUtils;

@Path("info")
public class DemoResource {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService es = Executors.newCachedThreadPool();

    final static String GENDER_SERVER = "https://api.genderize.io/?name=kim";
    final static String COUNTRY_SERVER = "https://api.nationalize.io/?name=kim";

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;
    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {
        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("select u from User u", entities.User.class);
            List<User> users = query.getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("name")
    public String getName() throws IOException, InterruptedException, ExecutionException, TimeoutException {

        String gender = HttpUtils.fetchData(GENDER_SERVER);
        GenderDTO genderDTO = gson.fromJson(gender, GenderDTO.class);

        String country = HttpUtils.fetchData(COUNTRY_SERVER);
        CountryDTO countryDTO = gson.fromJson(country, CountryDTO.class);

        CombinedNamesDTO combinedDTO = new CombinedNamesDTO(genderDTO, countryDTO);
       
       String combinedJSON = gson.toJson(combinedDTO);
       
       return combinedJSON;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }
}
