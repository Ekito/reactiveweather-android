package fr.ekito.myweatherlibrary.json.weather;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class TxtForecast {

    @Expose
    private String date;
    @Expose
    private List<Forecastday> forecastday = new ArrayList<Forecastday>();

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The forecastday
     */
    public List<Forecastday> getForecastday() {
        return forecastday;
    }

    /**
     * @param forecastday The forecastday
     */
    public void setForecastday(List<Forecastday> forecastday) {
        this.forecastday = forecastday;
    }

}
