package fr.ekito.myweatherapp;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import fr.ekito.myweatherlibrary.WeatherSDK;
import fr.ekito.myweatherlibrary.json.geocode.Geocode;
import fr.ekito.myweatherlibrary.json.geocode.Location;
import fr.ekito.myweatherlibrary.json.geocode.Result;
import fr.ekito.myweatherlibrary.json.weather.Forecastday_;
import fr.ekito.myweatherlibrary.json.weather.Simpleforecast;
import fr.ekito.myweatherlibrary.json.weather.Weather;
import rx.Observer;

import static fr.ekito.myweatherapp.WeatherFormat.displayWeatherIcon;
import static fr.ekito.myweatherapp.WeatherFormat.filterForecast;
import static fr.ekito.myweatherapp.WeatherFormat.getTemp;

public class MainActivity extends AppCompatActivity {

    private TextView mainTxt;
    private TextView day1Icon;
    private TextView day2Icon;
    private TextView day3Icon;
    private TextView mainIcon;
    private TextView day1Txt;
    private TextView day2Txt;
    private TextView day3Txt;
    private TextView day1Temp;
    private TextView day2Temp;
    private TextView day3Temp;
    private TextView weatherTitle;

    private AsyncTask asyncTask;
    private LinearLayout loadingLayout;
    private LinearLayout mainLayout;
    private Date now;
    private String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainIcon = (TextView) findViewById(R.id.weather_mainicon);
        mainTxt = (TextView) findViewById(R.id.weather_main);

        day1Icon = (TextView) findViewById(R.id.weather_day1);
        day1Txt = (TextView) findViewById(R.id.weather_daytext1);
        day1Temp = (TextView) findViewById(R.id.weather_temptext1);

        day2Icon = (TextView) findViewById(R.id.weather_day2);
        day2Txt = (TextView) findViewById(R.id.weather_daytext2);
        day2Temp = (TextView) findViewById(R.id.weather_temptext2);

        day3Icon = (TextView) findViewById(R.id.weather_day3);
        day3Txt = (TextView) findViewById(R.id.weather_daytext3);
        day3Temp = (TextView) findViewById(R.id.weather_temptext3);
        loadingLayout = (LinearLayout) findViewById(R.id.weather_loadlayout);
        mainLayout = (LinearLayout) findViewById(R.id.weather_mainlayout);

        weatherTitle = (TextView) findViewById(R.id.weather_title);

        loadingLayout.setVisibility(View.VISIBLE);
        mainLayout.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                popLocationDialog(view);
            }
        });
    }

    private void popLocationDialog(final View view) {
        final EditText input = new EditText(MainActivity.this);
        input.setHint("Paris, France");
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(R.string.location_title)
                .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        getWeather(view, input.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        dialog.setView(input);
        dialog.show();
    }

    private void getWeather(final View view, String address) {
        Snackbar.make(view, "Getting your weather :)", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        this.address = address;

        WeatherSDK.getGeocode(address).subscribe(new Observer<Geocode>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(view, "Geocode Error : " + e, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            @Override
            public void onNext(Geocode geocode) {
                List<Result> results = geocode.getResults();
                if (results.size() > 0) {
                    Location location = results.get(0).getGeometry().getLocation();
                    WeatherSDK.getWeather(location.getLat(), location.getLng()).subscribe(new Observer<Weather>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Snackbar.make(view, "Weather Error : " + e, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                        @Override
                        public void onNext(Weather weather) {
                            updateWeather(weather);
                        }
                    });
                } else {
                    Snackbar.make(view, "No result in geocode :(", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    public void updateWeather(Weather weather) {
        loadingLayout.setVisibility(View.GONE);
        mainLayout.setVisibility(View.VISIBLE);

        now = new Date();
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(MainApplication.get());
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(MainApplication.get());
        weatherTitle.setText(getString(R.string.weather_title) + " " + address + "\n" + dateFormat.format(now) + " " + timeFormat.format(now));

        if (weather.getForecast() != null) {
            Simpleforecast txtForecast = weather.getForecast().getSimpleforecast();
            List<Forecastday_> forecastday = filterForecast(txtForecast.getForecastday());
            Forecastday_ day0 = forecastday.get(0);
            mainTxt.setText("Today : " + weatherTxt(day0) + "\n" + getTemp(day0));
            displayWeatherIcon(mainIcon, 100, day0);

            displayWeatherIcon(day1Icon, 50, forecastday.get(1));
            Forecastday_ day1 = forecastday.get(1);
            day1Txt.setText("Day +1");
            day1Temp.setText(getTemp(day1));

            Forecastday_ day2 = forecastday.get(2);
            displayWeatherIcon(day2Icon, 50, day2);
            day2Txt.setText("Day +2");
            day2Temp.setText(getTemp(day2));

            Forecastday_ day3 = forecastday.get(3);
            displayWeatherIcon(day3Icon, 50, day3);
            day3Txt.setText("Day +3");
            day3Temp.setText(getTemp(day3));

        }

    }

    private String weatherTxt(Forecastday_ forecastday) {
        return forecastday.getConditions(); //.getTitle() + "\n" + forecastday.getFcttextMetric();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
