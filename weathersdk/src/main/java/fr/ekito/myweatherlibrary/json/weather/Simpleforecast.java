package fr.ekito.myweatherlibrary.json.weather;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Simpleforecast {

    @Expose
    private List<Forecastday_> forecastday = new ArrayList<Forecastday_>();

    /**
     * @return The forecastday
     */
    public List<Forecastday_> getForecastday() {
        return forecastday;
    }

    /**
     * @param forecastday The forecastday
     */
    public void setForecastday(List<Forecastday_> forecastday) {
        this.forecastday = forecastday;
    }

}
