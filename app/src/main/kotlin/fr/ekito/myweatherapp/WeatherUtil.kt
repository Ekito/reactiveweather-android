package fr.ekito.myweatherapp

import android.widget.TextView
import com.joanzapata.iconify.widget.IconTextView
import fr.ekito.myweatherlibrary.json.geocode.Geocode
import fr.ekito.myweatherlibrary.json.geocode.Location
import fr.ekito.myweatherlibrary.json.weather.Forecastday_

/**
 * Created by arnaud on 04/08/2016.
 */

//extensions
fun Geocode.getLocation(): Location? {
    return results.first().geometry?.location
}

fun Forecastday_.weatherTxt(): String {
    return conditions!! //.getTitle() + "\n" + forecastday.getFcttextMetric();
}

fun Forecastday_.getTemp(): String {
    return low!!.celsius + "°C - " + high!!.celsius + "°C"
}

// helper
object WeatherUtil {

    fun filterForecast(forecastday: List<Forecastday_>): List<Forecastday_> {
        return forecastday.filter { f -> !f.icon!!.startsWith("nt_") }
                .take(4)
    }

    fun updateWeatherIcon(txt: IconTextView, size: Int, f: Forecastday_) {
        txt.text = getWeatherCode(f.icon!!)
        txt.textSize = size.toFloat()
    }

    fun getWeatherCode(icon: String): String {
        val description = icon.replace("nt_", "")
        when {
            description.contains("rain") -> return "{wi_day_rain}"
            description.contains("cloudy") -> return "{wi_day_cloudy}"
            else -> return "{wi_day_sunny}"
        }
    }
}
