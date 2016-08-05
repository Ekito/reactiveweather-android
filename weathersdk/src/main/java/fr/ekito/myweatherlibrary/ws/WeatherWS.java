package fr.ekito.myweatherlibrary.ws;

import fr.ekito.myweatherlibrary.json.geocode.Geocode;
import fr.ekito.myweatherlibrary.json.weather.Weather;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by arnaud on 04/08/2016.
 */
public interface WeatherWS {

    @GET("/geocode")
    @Headers("Content-type: application/json")
    Observable<Geocode> geocode(@Query("address") String address);

    @GET("/weather")
    @Headers("Content-type: application/json")
    Observable<Weather> weather(@Query("lat") Double lat, @Query("lon") Double lon, @Query("lang") String lang);

}
