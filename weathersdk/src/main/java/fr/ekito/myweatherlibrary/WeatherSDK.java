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
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    public static Observable<Geocode> getGeocode(final String address) {
        return Injector.get(WeatherService.class).geocode(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Weather> getWeather(final Double lat, final Double lng) {
        return Injector.get(WeatherService.class).weather(lat, lng, "EN")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
