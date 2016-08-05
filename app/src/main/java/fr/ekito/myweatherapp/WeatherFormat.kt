package fr.ekito.myweatherapp

import android.widget.TextView
import fr.ekito.myweatherlibrary.json.weather.Forecastday_

/**
 * Created by arnaud on 04/08/2016.
 */
object WeatherFormat {
    
    fun weatherTxt(forecastday: Forecastday_): String {
        return forecastday.conditions //.getTitle() + "\n" + forecastday.getFcttextMetric();
    }

    fun filterForecast(forecastday: List<Forecastday_>): List<Forecastday_> {
        return forecastday.filter { f -> !f.icon.startsWith("nt_") }
                .take(4)
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
        val icon = icon.replace("nt_".toRegex(), "")
        when (icon) {
            "cloudy" -> return Character.toString(0x049.toChar())
            "partlycloudy" -> return Character.toString(0x022.toChar())
            else -> return Character.toString(0x049.toChar())
        }
    }
}
