package fr.ekito.myweatherlibrary.json.weather;

import com.google.gson.annotations.Expose;

public class Maxwind {

    @Expose
    private Integer mph;
    @Expose
    private Integer kph;
    @Expose
    private String dir;
    @Expose
    private Integer degrees;

    /**
     * @return The mph
     */
    public Integer getMph() {
        return mph;
    }

    /**
     * @param mph The mph
     */
    public void setMph(Integer mph) {
        this.mph = mph;
    }

    /**
     * @return The kph
     */
    public Integer getKph() {
        return kph;
    }

    /**
     * @param kph The kph
     */
    public void setKph(Integer kph) {
        this.kph = kph;
    }

    /**
     * @return The dir
     */
    public String getDir() {
        return dir;
    }

    /**
     * @param dir The dir
     */
    public void setDir(String dir) {
        this.dir = dir;
    }

    /**
     * @return The degrees
     */
    public Integer getDegrees() {
        return degrees;
    }

    /**
     * @param degrees The degrees
     */
    public void setDegrees(Integer degrees) {
        this.degrees = degrees;
    }

}
