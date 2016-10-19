package fr.ekito.myweatherapp

import android.view.View

/**
 * Created by arnaud on 19/10/2016.
 */
interface WeatherCallback {
    fun getWeatherData(view: View, location: String)
}