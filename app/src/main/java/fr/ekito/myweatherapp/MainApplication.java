package fr.ekito.myweatherapp;

import android.app.Application;

import fr.ekito.myweatherlibrary.WeatherSDK;

/**
 * Created by arnaud on 04/08/2016.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // init sdk
        WeatherSDK.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        WeatherSDK.close();
    }
}
