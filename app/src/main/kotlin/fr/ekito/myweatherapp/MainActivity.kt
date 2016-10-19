package fr.ekito.myweatherapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import fr.ekito.myweatherapp.model.Weather
import fr.ekito.myweatherlibrary.WeatherSDK
import fr.ekito.myweatherlibrary.json.geocode.Geocode
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), WeatherCallback {

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
                .map { weather -> Weather.from(location, weather) }
                .subscribe(
                        { weather -> updateWeatherUI(weather) },
                        { error -> Snackbar.make(view, "Weather Error : $error", Snackbar.LENGTH_LONG).show() }
                )
    }

    /**
     * update UI from weather result
     */
    fun updateWeatherUI(weather: Weather) {
        weather_loadlayout.visibility = View.GONE
        weather_mainlayout.visibility = View.VISIBLE

        val timeFormat = android.text.format.DateFormat.getTimeFormat(MainApplication.get())
        val dateFormat = android.text.format.DateFormat.getDateFormat(MainApplication.get())
        weather_title.text = "${getString(R.string.weather_title)} ${weather.location} \n ${dateFormat.format(weather.date)} ${timeFormat.format(weather.date)}"

        weather_main.text = "Today : ${weather.detail.title}\n${weather.detail.temp}" // Use extensions :)
        weather_mainicon.text = weather.detail.icon

        val day1 = weather.nextDays[0]
        weather_daytext1.text = "day1 :\n ${day1.title}"
        weather_day1.text = day1.icon
        weather_temptext1.text = day1.temp

        val day2 = weather.nextDays[1]
        weather_daytext2.text = "day2 :\n ${day2.title}"
        weather_day2.text = day2.icon
        weather_temptext2.text = day2.temp

        val day3 = weather.nextDays[2]
        weather_daytext3.text = "day3 :\n ${day3.title}"
        weather_day3.text = day3.icon
        weather_temptext3.text = day3.temp
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
