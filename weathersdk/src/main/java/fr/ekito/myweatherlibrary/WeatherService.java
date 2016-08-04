package fr.ekito.myweatherlibrary;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import fr.ekito.myweatherlibrary.di.Injector;
import fr.ekito.myweatherlibrary.json.geocode.Geocode;
import fr.ekito.myweatherlibrary.ws.WeatherWS;

public class WeatherService extends Service {

    private static final String TAG = WeatherService.class.getSimpleName();
    private final IBinder mBinder = new LocalBinder();
    private WeatherWS weatherWS;

    public WeatherService() {
    }

    public Geocode geocode(String address) {
        Geocode geocode = null;
        try {
            geocode = weatherWS.geocode(address);
        } catch (Throwable e) {
            e.printStackTrace();
            Log.e(TAG, "error with geocoding " + e);
        }
        return geocode;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // inject stuff
        weatherWS = Injector.get(WeatherWS.class);

        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // close stuff
        return super.onUnbind(intent);
    }

    public class LocalBinder extends Binder {
        public WeatherService getService() {
            return WeatherService.this;
        }
    }
}
