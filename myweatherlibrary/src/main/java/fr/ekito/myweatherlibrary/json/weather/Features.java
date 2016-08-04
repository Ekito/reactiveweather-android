package fr.ekito.myweatherlibrary.json.weather;

import com.google.gson.annotations.Expose;

public class Features {

    @Expose
    private Integer forecast;

    /**
     * @return The forecast
     */
    public Integer getForecast() {
        return forecast;
    }

    /**
     * @param forecast The forecast
     */
    public void setForecast(Integer forecast) {
        this.forecast = forecast;
    }

}
