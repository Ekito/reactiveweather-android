package fr.ekito.myweatherapp

import android.widget.TextView

import java.util.ArrayList

import fr.ekito.myweatherlibrary.json.weather.Forecastday_

/**
 * Created by arnaud on 04/08/2016.
 */
object WeatherFormat {


    fun weatherTxt(forecastday: Forecastday_): String {
        return forecastday.conditions //.getTitle() + "\n" + forecastday.getFcttextMetric();
    }

    fun filterForecast(forecastday: List<Forecastday_>): List<Forecastday_> {
        val filtered = ArrayList<Forecastday_>()

        println("")
        for (f in forecastday) {
            if (!f.icon.startsWith("nt_")) {
                filtered.add(f)
                if (filtered.size >= 4) {
                    break
                }
            }
        }
        return filtered
    }

    fun getTemp(day1: Forecastday_): String {
        return day1.low.celsius + "°C - " + day1.high.celsius + "°C"
    }

    fun displayWeatherIcon(txt: TextView, size: Int, f: Forecastday_) {
        txt.typeface = MainApplication.getClimaconFont()
        txt.text = getWeatherCode(f.icon)
        txt.textSize = size.toFloat()
    }

    fun getWeatherCode(icon: String): String {
        var icon = icon
        icon = icon.replace("nt_".toRegex(), "")
        if (icon == "cloudy") {
            return Character.toString(0x049.toChar())
        } else if (icon == "partlycloudy") {
            return Character.toString(0x022.toChar())
        } else
            return Character.toString(0x049.toChar())
    }
}
