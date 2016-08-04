package fr.ekito.myweatherlibrary.di.module;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import fr.ekito.myweatherlibrary.WeatherService;
import fr.ekito.myweatherlibrary.di.Injector;
import fr.ekito.myweatherlibrary.di.Module;
import fr.ekito.myweatherlibrary.ws.WeatherWS;
import retrofit.RestAdapter;
import retrofit.client.OkClient;


/**
 * Created by agiuliani on 20/04/2016.
 */
public class MainModule extends Module {

    private static final String TAG = MainModule.class.getSimpleName();

    @Override
    public void load() {
        provide(registerService(), ServiceConnection.class);
        provide(weatherWs(), WeatherWS.class);
    }

    ServiceConnection registerService() {
        return new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.w(TAG, "WeatherService onServiceConnected");
                WeatherService serviceConnection = ((WeatherService.LocalBinder) service).getService();
                Injector.add(serviceConnection, WeatherService.class);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.w(TAG, "WeatherService onServiceDisconnected");
                Injector.remove(WeatherService.class);
            }
        };
    }


    WeatherWS weatherWs() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(60l, TimeUnit.SECONDS);
        client.setReadTimeout(60l, TimeUnit.SECONDS);

        RestAdapter.LogLevel level = RestAdapter.LogLevel.FULL;
        RestAdapter ra = new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setLogLevel(level)
                .setEndpoint("https://my-weather-api.herokuapp.com/")
                .build();
        return ra.create(WeatherWS.class);
    }
}
