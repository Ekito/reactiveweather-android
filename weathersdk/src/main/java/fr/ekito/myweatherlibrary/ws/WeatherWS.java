package fr.ekito.myweatherlibrary.ws;

import fr.ekito.myweatherlibrary.json.geocode.Geocode;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by arnaud on 04/08/2016.
 */
public interface WeatherWS {

    @GET("/geocode")
    @Headers("Content-type: application/json")
    Geocode geocode(@Query("address") String address);

}
