package fr.ekito.myweatherlibrary.ws

import fr.ekito.myweatherlibrary.json.geocode.Geocode
import fr.ekito.myweatherlibrary.json.weather.Weather
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import rx.Observable

/**
 * Created by arnaud on 04/08/2016.
 */
interface WeatherWS {

    @GET("/geocode")
    @Headers("Content-type: application/json")
    fun geocode(@Query("address") address: String): Observable<Geocode>

    @GET("/weather")
    @Headers("Content-type: application/json")
    fun weather(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("lang") lang: String): Observable<Weather>

}
