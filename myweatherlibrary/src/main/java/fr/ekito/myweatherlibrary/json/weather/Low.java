package fr.ekito.myweatherlibrary.json.weather;

import com.google.gson.annotations.Expose;

public class Low {

    @Expose
    private String fahrenheit;
    @Expose
    private String celsius;

    /**
     * @return The fahrenheit
     */
    public String getFahrenheit() {
        return fahrenheit;
    }

    /**
     * @param fahrenheit The fahrenheit
     */
    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    /**
     * @return The celsius
     */
    public String getCelsius() {
        return celsius;
    }

    /**
     * @param celsius The celsius
     */
    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

}
