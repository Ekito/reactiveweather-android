package fr.ekito.myweatherlibrary

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import fr.ekito.myweatherlibrary.di.KInjector
import fr.ekito.myweatherlibrary.json.geocode.Geocode
import fr.ekito.myweatherlibrary.json.weather.Weather
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Weather SDK
 */
object WeatherSDK {

    private val TAG = WeatherSDK::class.java.simpleName

    /**
     * INit SDK and context
     */
    fun init(context: Application) {
        KInjector.context = context

        // connect to service
        Log.i(TAG, "connect to service ...")
        val serviceIntent = Intent(context, WeatherService::class.java)
        context.bindService(serviceIntent, KInjector.serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun close() {
        Log.i(TAG, "shutdown ...")
        val applicationContext = KInjector.context
        // unbind service
        applicationContext!!.unbindService(KInjector.serviceConnection)
        KInjector.close()
    }

    /**
     * Get observable geocode for given address
     */
    fun getGeocode(address: String): Observable<Geocode> {
        return KInjector.weatherWS.geocode(address).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Get observable weather for given location
     */
    fun getWeather(lat: Double, lng: Double): Observable<Weather> {
        return KInjector.weatherWS.weather(lat, lng, "EN").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}
