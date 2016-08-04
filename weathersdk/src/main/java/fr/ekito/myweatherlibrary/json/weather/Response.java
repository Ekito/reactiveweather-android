package fr.ekito.myweatherlibrary.json.weather;

import com.google.gson.annotations.Expose;

public class Response {

    @Expose
    private String version;
    @Expose
    private String termsofService;
    @Expose
    private Features features;

    /**
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return The termsofService
     */
    public String getTermsofService() {
        return termsofService;
    }

    /**
     * @param termsofService The termsofService
     */
    public void setTermsofService(String termsofService) {
        this.termsofService = termsofService;
    }

    /**
     * @return The features
     */
    public Features getFeatures() {
        return features;
    }

    /**
     * @param features The features
     */
    public void setFeatures(Features features) {
        this.features = features;
    }

}
