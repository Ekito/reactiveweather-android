package fr.ekito.myweatherapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import fr.ekito.myweatherlibrary.WeatherSDK;
import fr.ekito.myweatherlibrary.json.geocode.Geocode;
import fr.ekito.myweatherlibrary.json.geocode.Location;
import fr.ekito.myweatherlibrary.json.geocode.Result;
import fr.ekito.myweatherlibrary.json.weather.Weather;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView textView = (TextView) findViewById(R.id.the_text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Snackbar.make(view, "Start !", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                final String address = "Toulouse, France";
                WeatherSDK.getGeocode(address, new WeatherSDK.Callback<Geocode>() {
                    @Override
                    public void onSuccess(Geocode geocode) {
                        List<Result> results = geocode.getResults();
                        if (results.size() > 0) {
                            Location location = results.get(0).getGeometry().getLocation();
                            WeatherSDK.getWeather(location.getLat(), location.getLng(), new WeatherSDK.Callback<Weather>() {
                                @Override
                                public void onSuccess(Weather weather) {
                                    textView.setText("weather for :" + address + "...");
                                }

                                @Override
                                public void onError(Exception error) {
                                    Snackbar.make(view, "Weather Error : " + error, Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            });
                        } else {
                            Snackbar.make(view, "No result in geocode :(", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }

                    @Override
                    public void onError(Exception error) {
                        Snackbar.make(view, "Geocode Error : " + error, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
            }
        });
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
