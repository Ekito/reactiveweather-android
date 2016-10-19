package fr.ekito.myweatherapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import fr.ekito.myweatherlibrary.WeatherSDK
import fr.ekito.myweatherlibrary.json.geocode.Geocode
import fr.ekito.myweatherlibrary.json.weather.Weather
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity(), WeatherCallback {

    lateinit var now: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view -> DialogHelper.locationDialog(view, this@MainActivity) }

        weather_loadlayout.visibility = View.VISIBLE
        weather_mainlayout.visibility = View.GONE
    }

    /**
     * get the weather
     */
    override fun getWeatherData(view: View, location: String) {
        Snackbar.make(view, "Getting your weather :)", Snackbar.LENGTH_SHORT).show()

        WeatherSDK.getGeocode(location) //Observable<Geocode>
                .map(Geocode::getLocation) //map directly to function
                .switchMap { location -> WeatherSDK.getWeather(location!!.lat!!, location.lng!!) } // explicit NPE if no location
                .doOnError { e -> Snackbar.make(view, "Weather Error : $e", Snackbar.LENGTH_LONG).show() }
                .onErrorReturn { t -> null } // catch uncaught exception & return null
                .subscribe { weather -> if (weather != null) updateWeatherUI(weather, location) }
    }

    /**
     * update UI from weather result
     */
    fun updateWeatherUI(weather: Weather, address: String) {
        weather_loadlayout.visibility = View.GONE
        weather_mainlayout.visibility = View.VISIBLE

        now = Date()
        val timeFormat = android.text.format.DateFormat.getTimeFormat(MainApplication.get())
        val dateFormat = android.text.format.DateFormat.getDateFormat(MainApplication.get())
        weather_title.text = getString(R.string.weather_title) + " " + address + "\n" + dateFormat.format(now) + " " + timeFormat.format(now)

        if (weather.forecast != null) {
            val txtForecast = weather.forecast?.simpleforecast
            val forecastday = WeatherUtil.filterForecast(txtForecast!!.forecastday)
            val day0 = forecastday[0]
            weather_main.text = "Today : ${day0.weatherTxt()}\n${day0.getTemp()}" // Use extensions :)
            WeatherUtil.updateWeatherIcon(weather_mainicon, 100, day0)

            WeatherUtil.updateWeatherIcon(weather_day1, 50, forecastday[1])
            val day1 = forecastday[1]
            weather_temptext1.text = day1.getTemp()

            val day2 = forecastday[2]
            WeatherUtil.updateWeatherIcon(weather_day2, 50, day2)
            weather_temptext2.text = day2.getTemp()

            val day3 = forecastday[3]
            WeatherUtil.updateWeatherIcon(weather_day3, 50, day3)
            weather_temptext3.text = day3.getTemp()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
