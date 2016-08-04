package fr.ekito.myweatherlibrary.json.geocode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Viewport {

    @SerializedName("northeast")
    @Expose
    private Northeast northeast;
    @SerializedName("southwest")
    @Expose
    private Southwest southwest;

    /**
     * @return The northeast
     */
    public Northeast getNortheast() {
        return northeast;
    }

    /**
     * @param northeast The northeast
     */
    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    /**
     * @return The southwest
     */
    public Southwest getSouthwest() {
        return southwest;
    }

    /**
     * @param southwest The southwest
     */
    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

}
