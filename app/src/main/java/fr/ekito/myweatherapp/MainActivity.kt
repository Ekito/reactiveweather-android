package fr.ekito.myweatherapp

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import fr.ekito.myweatherlibrary.WeatherSDK
import fr.ekito.myweatherlibrary.json.weather.Weather
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var now: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view -> popLocationDialog(view) }

        weather_loadlayout.visibility = View.VISIBLE
        weather_mainlayout.visibility = View.GONE
    }

    fun popLocationDialog(view: View) {
        val input = EditText(this@MainActivity)
        input.hint = "i.e: Paris, France"

        val builder = AlertDialog.Builder(view.context)
        builder.setMessage(R.string.location_title).setPositiveButton(R.string.search) { dialog, id ->
            dialog.dismiss()
            getWeather(view, input.text.toString())
        }.setNegativeButton(R.string.cancel) { dialog, id ->
            // User cancelled the dialog
            dialog.dismiss()
        }
        // Create the AlertDialog object and return it
        val dialog = builder.create()
        val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        input.layoutParams = lp
        dialog.setView(input)
        dialog.show()
    }

    fun getWeather(view: View, address: String) {
        Snackbar.make(view, "Getting your weather :)", Snackbar.LENGTH_SHORT).setAction("Action", null).show()

        WeatherSDK.getGeocode(address)
                .map { geocode -> WeatherFormat.getLocation(geocode) }
                .switchMap { location -> WeatherSDK.getWeather(location!!.lat, location!!.lng) }
                .doOnError { e -> Snackbar.make(view, "Weather Error : " + e, Snackbar.LENGTH_LONG).setAction("Action", null).show() }
                .subscribe { weather -> updateWeather(weather, address) }
    }

    fun updateWeather(weather: Weather, address: String) {
        weather_loadlayout.visibility = View.GONE
        weather_mainlayout.visibility = View.VISIBLE

        now = Date()
        val timeFormat = android.text.format.DateFormat.getTimeFormat(MainApplication.get())
        val dateFormat = android.text.format.DateFormat.getDateFormat(MainApplication.get())
        weather_title.text = getString(R.string.weather_title) + " " + address + "\n" + dateFormat.format(now) + " " + timeFormat.format(now)

        if (weather.forecast != null) {
            val txtForecast = weather.forecast.simpleforecast
            val forecastday = WeatherFormat.filterForecast(txtForecast.forecastday)
            val day0 = forecastday[0]
            weather_main.text = "Today : " + WeatherFormat.weatherTxt(day0) + "\n" + WeatherFormat.getTemp(day0)
            WeatherFormat.displayWeatherIcon(weather_mainicon, 100, day0)

            WeatherFormat.displayWeatherIcon(weather_day1, 50, forecastday[1])
            val day1 = forecastday[1]
            weather_temptext1.text = WeatherFormat.getTemp(day1)

            val day2 = forecastday[2]
            WeatherFormat.displayWeatherIcon(weather_day2, 50, day2)
            weather_temptext2.text = WeatherFormat.getTemp(day2)

            val day3 = forecastday[3]
            WeatherFormat.displayWeatherIcon(weather_day3, 50, day3)
            weather_temptext3.text = WeatherFormat.getTemp(day3)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
