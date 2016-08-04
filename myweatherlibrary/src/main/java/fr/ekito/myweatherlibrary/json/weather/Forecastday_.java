package fr.ekito.myweatherlibrary.json.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecastday_ {

    @Expose
    private Date date;
    @Expose
    private Integer period;
    @Expose
    private High high;
    @Expose
    private Low low;
    @Expose
    private String conditions;
    @Expose
    private String icon;
    @SerializedName("icon_url")
    @Expose
    private String iconUrl;
    @Expose
    private String skyicon;
    @Expose
    private Integer pop;
    @SerializedName("qpf_allday")
    @Expose
    private QpfAllday qpfAllday;
    @SerializedName("qpf_day")
    @Expose
    private QpfDay qpfDay;
    @SerializedName("qpf_night")
    @Expose
    private QpfNight qpfNight;
    @SerializedName("snow_allday")
    @Expose
    private SnowAllday snowAllday;
    @SerializedName("snow_day")
    @Expose
    private SnowDay snowDay;
    @SerializedName("snow_night")
    @Expose
    private SnowNight snowNight;
    @Expose
    private Maxwind maxwind;
    @Expose
    private Avewind avewind;
    @Expose
    private Integer avehumidity;
    @Expose
    private Integer maxhumidity;
    @Expose
    private Integer minhumidity;

    /**
     * @return The date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return The period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * @param period The period
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * @return The high
     */
    public High getHigh() {
        return high;
    }

    /**
     * @param high The high
     */
    public void setHigh(High high) {
        this.high = high;
    }

    /**
     * @return The low
     */
    public Low getLow() {
        return low;
    }

    /**
     * @param low The low
     */
    public void setLow(Low low) {
        this.low = low;
    }

    /**
     * @return The conditions
     */
    public String getConditions() {
        return conditions;
    }

    /**
     * @param conditions The conditions
     */
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    /**
     * @return The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return The iconUrl
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * @param iconUrl The icon_url
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * @return The skyicon
     */
    public String getSkyicon() {
        return skyicon;
    }

    /**
     * @param skyicon The skyicon
     */
    public void setSkyicon(String skyicon) {
        this.skyicon = skyicon;
    }

    /**
     * @return The pop
     */
    public Integer getPop() {
        return pop;
    }

    /**
     * @param pop The pop
     */
    public void setPop(Integer pop) {
        this.pop = pop;
    }

    /**
     * @return The qpfAllday
     */
    public QpfAllday getQpfAllday() {
        return qpfAllday;
    }

    /**
     * @param qpfAllday The qpf_allday
     */
    public void setQpfAllday(QpfAllday qpfAllday) {
        this.qpfAllday = qpfAllday;
    }

    /**
     * @return The qpfDay
     */
    public QpfDay getQpfDay() {
        return qpfDay;
    }

    /**
     * @param qpfDay The qpf_day
     */
    public void setQpfDay(QpfDay qpfDay) {
        this.qpfDay = qpfDay;
    }

    /**
     * @return The qpfNight
     */
    public QpfNight getQpfNight() {
        return qpfNight;
    }

    /**
     * @param qpfNight The qpf_night
     */
    public void setQpfNight(QpfNight qpfNight) {
        this.qpfNight = qpfNight;
    }

    /**
     * @return The snowAllday
     */
    public SnowAllday getSnowAllday() {
        return snowAllday;
    }

    /**
     * @param snowAllday The snow_allday
     */
    public void setSnowAllday(SnowAllday snowAllday) {
        this.snowAllday = snowAllday;
    }

    /**
     * @return The snowDay
     */
    public SnowDay getSnowDay() {
        return snowDay;
    }

    /**
     * @param snowDay The snow_day
     */
    public void setSnowDay(SnowDay snowDay) {
        this.snowDay = snowDay;
    }

    /**
     * @return The snowNight
     */
    public SnowNight getSnowNight() {
        return snowNight;
    }

    /**
     * @param snowNight The snow_night
     */
    public void setSnowNight(SnowNight snowNight) {
        this.snowNight = snowNight;
    }

    /**
     * @return The maxwind
     */
    public Maxwind getMaxwind() {
        return maxwind;
    }

    /**
     * @param maxwind The maxwind
     */
    public void setMaxwind(Maxwind maxwind) {
        this.maxwind = maxwind;
    }

    /**
     * @return The avewind
     */
    public Avewind getAvewind() {
        return avewind;
    }

    /**
     * @param avewind The avewind
     */
    public void setAvewind(Avewind avewind) {
        this.avewind = avewind;
    }

    /**
     * @return The avehumidity
     */
    public Integer getAvehumidity() {
        return avehumidity;
    }

    /**
     * @param avehumidity The avehumidity
     */
    public void setAvehumidity(Integer avehumidity) {
        this.avehumidity = avehumidity;
    }

    /**
     * @return The maxhumidity
     */
    public Integer getMaxhumidity() {
        return maxhumidity;
    }

    /**
     * @param maxhumidity The maxhumidity
     */
    public void setMaxhumidity(Integer maxhumidity) {
        this.maxhumidity = maxhumidity;
    }

    /**
     * @return The minhumidity
     */
    public Integer getMinhumidity() {
        return minhumidity;
    }

    /**
     * @param minhumidity The minhumidity
     */
    public void setMinhumidity(Integer minhumidity) {
        this.minhumidity = minhumidity;
    }

}
