package fr.ekito.myweatherapp.model

import fr.ekito.myweatherapp.WeatherUtil
import fr.ekito.myweatherapp.getTemp
import fr.ekito.myweatherapp.weatherTxt
import fr.ekito.myweatherlibrary.json.weather.Weather
import io.realm.RealmList
import io.realm.RealmObject
import java.util.*

/**
 * Created by arnaud on 19/10/2016.
 */
open class Weather constructor(var location: String = "", var date: Date = Date()) : RealmObject() {

    open lateinit var detail: WeatherDetail
    open var nextDays: RealmList<WeatherDetail> = RealmList()

    companion object {
        fun from(location: String, weather: Weather): fr.ekito.myweatherapp.model.Weather {
            val w = Weather(location.trim().replace(" ", "").toLowerCase(), Date())
            //weather details
            if (weather.forecast != null) {
                val txtForecast = weather.forecast?.simpleforecast
                val forecastday = txtForecast!!.forecastday.take(4)
                val day0 = forecastday[0]
                w.detail = WeatherDetail(day0.weatherTxt(), WeatherUtil.getWeatherCode(day0), day0.getTemp())

                for (i in 1..(forecastday.size - 1)) {//take 3 days
                    val day = forecastday[i]
                    w.nextDays.add(WeatherDetail(day.weatherTxt(), WeatherUtil.getWeatherCode(day), day.getTemp()))
                }
            }
            return w
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is fr.ekito.myweatherapp.model.Weather) {
            return location.equals(other.location)
        } else return false
    }
}