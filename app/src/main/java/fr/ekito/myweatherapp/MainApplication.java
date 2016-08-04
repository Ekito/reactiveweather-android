package fr.ekito.myweatherapp;

import android.app.Application;
import android.graphics.Typeface;

import fr.ekito.myweatherlibrary.WeatherSDK;

/**
 * Created by arnaud on 04/08/2016.
 */
public class MainApplication extends Application {

    private static Typeface climaconFont;
    private static MainApplication instance;

    public static Typeface getClimaconFont() {
        return climaconFont;
    }

    public static Application get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // init sdk
        WeatherSDK.init(this);

        climaconFont = Typeface.createFromAsset(getAssets(), "fonts/climacons.ttf");

        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        WeatherSDK.close();
    }
}
