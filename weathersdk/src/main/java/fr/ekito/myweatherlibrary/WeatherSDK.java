package fr.ekito.myweatherlibrary;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;

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

    public static Geocode getGeocode(String address) {
        return Injector.get(WeatherService.class).geocode(address);

    }

    public interface Callback<T>{
        void onSucess(T result);
        void onError(Exception error);
    }
}
