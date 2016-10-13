package fr.ekito.myweatherapp

import android.app.Application
import android.graphics.Typeface
import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.WeathericonsModule

import fr.ekito.myweatherlibrary.WeatherSDK

/**
 * Created by arnaud on 04/08/2016.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // init sdk
        WeatherSDK.init(this)
        Iconify.with(WeathericonsModule())

        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
        WeatherSDK.close()
    }

    companion object {
        lateinit var instance: MainApplication

        fun get(): Application {
            return instance
        }
    }
}
