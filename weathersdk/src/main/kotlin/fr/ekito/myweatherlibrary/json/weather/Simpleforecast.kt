package fr.ekito.myweatherlibrary.json.weather

import com.google.gson.annotations.Expose

import java.util.ArrayList

class Simpleforecast {

    /**
     * @return The forecastday
     */
    /**
     * @param forecastday The forecastday
     */
    @Expose
    var forecastday: List<Forecastday_> = ArrayList()

}
