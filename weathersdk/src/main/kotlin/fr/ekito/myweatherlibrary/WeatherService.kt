package fr.ekito.myweatherlibrary

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import fr.ekito.myweatherlibrary.di.KInjector
import fr.ekito.myweatherlibrary.json.geocode.Geocode
import fr.ekito.myweatherlibrary.json.weather.Weather
import fr.ekito.myweatherlibrary.ws.WeatherWS
import rx.Observable

/**
 * Weather service
 */
class WeatherService : Service() {

    val mBinder = LocalBinder()
    lateinit var weatherWS: WeatherWS

    override fun onBind(intent: Intent): IBinder? {
        // inject stuff
        weatherWS = KInjector.weatherWS
        return mBinder
    }

    override fun onUnbind(intent: Intent): Boolean {
        // close stuff
        return super.onUnbind(intent)
    }

    inner class LocalBinder : Binder() {
        val service: WeatherService
            get() = this@WeatherService
    }

    /**
     * Get geocode for given address
     */
    fun geocode(address: String): Observable<Geocode> {
        return weatherWS.geocode(address)
    }

    /**
     * Get weather for given gps location
     * @see geocode to get gps location for given address
     */
    fun weather(lat: Double, lon: Double, lang: String): Observable<Weather> {
        return weatherWS.weather(lat, lon, lang)
    }
}
