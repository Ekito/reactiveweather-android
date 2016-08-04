package fr.ekito.myweatherlibrary.json.weather;

import com.google.gson.annotations.Expose;

public class Weather {

    @Expose
    private Response response;
    @Expose
    private Forecast forecast;

    /**
     * @return The response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(Response response) {
        this.response = response;
    }

    /**
     * @return The forecast
     */
    public Forecast getForecast() {
        return forecast;
    }

    /**
     * @param forecast The forecast
     */
    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

}
