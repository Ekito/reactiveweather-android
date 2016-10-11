package fr.ekito.myweatherapp

import android.app.Application
import android.graphics.Typeface

import fr.ekito.myweatherlibrary.WeatherSDK

/**
 * Created by arnaud on 04/08/2016.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // init sdk
        WeatherSDK.init(this)

        climaconFont = Typeface.createFromAsset(assets, "fonts/climacons.ttf")
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
        WeatherSDK.close()
    }

    companion object {
        lateinit var climaconFont: Typeface
        lateinit var instance: MainApplication

        fun get(): Application {
            return instance
        }
    }
}
