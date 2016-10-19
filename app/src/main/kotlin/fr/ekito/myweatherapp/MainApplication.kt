package fr.ekito.myweatherapp

import android.app.Application
import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.WeathericonsModule
import fr.ekito.myweatherlibrary.WeatherSDK
import io.realm.Realm

/**
 * Created by arnaud on 04/08/2016.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // init sdk
        WeatherSDK.init(this)
        Iconify.with(WeathericonsModule())
        Realm.init(this)

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
