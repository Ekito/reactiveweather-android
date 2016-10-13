package fr.ekito.myweatherlibrary.di

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import fr.ekito.myweatherlibrary.WeatherService
import fr.ekito.myweatherlibrary.ws.WeatherWS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Components injection
 */
object KInjector {
    val TAG = KInjector::class.java.simpleName

    var context: Context? = null

    var service: WeatherService? = null

    val serviceConnection: ServiceConnection by lazy {
        object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                Log.w(TAG, "WeatherService onServiceConnected")
                this@KInjector.service = (service as WeatherService.LocalBinder).service
            }

            override fun onServiceDisconnected(name: ComponentName) {
                Log.w(TAG, "WeatherService onServiceDisconnected")
                service = null
            }
        }
    }

    val httpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().connectTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).build()
    }

    val weatherWS: WeatherWS by lazy {
        val retrofit = Retrofit.Builder().baseUrl("https://my-weather-api.herokuapp.com/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        retrofit.create(WeatherWS::class.java)
    }

    fun close() {
        //close sdk resource
    }
}