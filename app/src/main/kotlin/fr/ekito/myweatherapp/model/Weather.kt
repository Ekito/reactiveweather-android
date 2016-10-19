package fr.ekito.myweatherapp.model

import fr.ekito.myweatherapp.WeatherUtil
import fr.ekito.myweatherapp.getTemp
import fr.ekito.myweatherapp.weatherTxt
import fr.ekito.myweatherlibrary.json.weather.Weather
import java.util.*

/**
 * Created by arnaud on 19/10/2016.
 */
class Weather(val location: String, val date: Date) {

    lateinit var detail: WeatherDetail
    var nextDays: ArrayList<WeatherDetail> = ArrayList()

    companion object {
        fun from(location: String, weather: Weather): fr.ekito.myweatherapp.model.Weather {
            val w = Weather(location, Date())
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
}