package fr.ekito.myweatherlibrary;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;

import java.util.Locale;

import fr.ekito.myweatherlibrary.di.Injector;
import fr.ekito.myweatherlibrary.di.module.MainModule;
import fr.ekito.myweatherlibrary.json.geocode.Geocode;
import fr.ekito.myweatherlibrary.json.weather.Weather;

/**
 * Created by arnaud on 04/08/2016.
 */
public class WeatherSDK {

    private static String TAG = WeatherSDK.class.getSimpleName();

    public static void init(Application context) {
        Injector.load(MainModule.class);

        Injector.add(context, Application.class);

        // connect to service
        Log.i(TAG, "connect to service ...");
        ServiceConnection serviceConnection = Injector.get(ServiceConnection.class);
        Intent serviceIntent = new Intent(context, WeatherService.class);
        context.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public static void close() {
        Log.i(TAG, "shutdown ...");

        Application applicationContext = Injector.get(Application.class);
        // unbind service
        applicationContext.unbindService(Injector.get(ServiceConnection.class));
        Injector.clear();
    }

    public static void getGeocode(final String address, final Callback<Geocode> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Geocode geocode = Injector.get(WeatherService.class).geocode(address);
                    callback.onSuccess(geocode);
                } catch (Exception e) {
                    callback.onError(e);
                }
            }
        }).start();
    }

    public static void getWeather(final Double lat, final Double lng, final Callback<Weather> callback) {
        final String language = Locale.getDefault().getLanguage();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Weather weather = Injector.get(WeatherService.class).weather(lat, lng, language);
                    callback.onSuccess(weather);
                } catch (Exception e) {
                    callback.onError(e);
                }
            }
        }).start();
    }

    public interface Callback<T> {
        void onSuccess(T result);

        void onError(Exception error);
    }
}
