package nameFetcher;

import com.google.gson.Gson;
import dtos.CombinedNamesDTO;
import dtos.CountryDTO;
import dtos.GenderDTO;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import utils.HttpUtils;



public class NameFetcher {
   
    final static String GENDER_SERVER = "https://api.genderize.io/?name=kim";
    final static String COUNTRY_SERVER = "https://api.nationalize.io/?name=kim";

    
public static String responseParallel (ExecutorService threadPool, final Gson gson) throws IOException, InterruptedException, ExecutionException, TimeoutException {
   
    Callable<GenderDTO> genderTask = new Callable<GenderDTO>(){
        @Override
        public GenderDTO call() throws IOException {
            String gender = HttpUtils.fetchData(GENDER_SERVER);
            GenderDTO genderDTO = gson.fromJson(gender, GenderDTO.class);
            return genderDTO;
        }
    };      
    
       Callable<CountryDTO> countryTask = new Callable<CountryDTO>(){
           @Override
           public CountryDTO call() throws IOException {
               String country = HttpUtils.fetchData(COUNTRY_SERVER);
               CountryDTO countryDTO = gson.fromJson(country, CountryDTO.class);
               return countryDTO;
           }
       };
         
       Future<GenderDTO> futureGender = threadPool.submit(genderTask);
       Future<CountryDTO> futureCountry = threadPool.submit(countryTask);
       
       GenderDTO gender = futureGender.get();
       CountryDTO country = futureCountry.get();
       
       CombinedNamesDTO combinedDTO = new CombinedNamesDTO(gender, country);
       
       String combinedJSON = gson.toJson(combinedDTO);
       
       return combinedJSON;
 
    }
}

