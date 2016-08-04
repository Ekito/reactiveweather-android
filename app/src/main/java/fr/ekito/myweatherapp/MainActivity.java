package fr.ekito.myweatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import fr.ekito.myweatherlibrary.WeatherSDK;
import fr.ekito.myweatherlibrary.di.Injector;
import fr.ekito.myweatherlibrary.json.geocode.Geocode;
import fr.ekito.myweatherlibrary.ws.WeatherWS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Snackbar.make(view, "Start !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                new AsyncTask<Void, Void, Geocode>() {
                    @Override
                    protected Geocode doInBackground(Void[] objects) {
                        Geocode geocode = WeatherSDK.getGeocode("Toulouse, France");
//                        WeatherWS weatherWS = Injector.get(WeatherWS.class);
//                        Geocode geocode = weatherWS.geocode("Toulouse, France");
                        return geocode;
                    }

                    @Override
                    protected void onPostExecute(Geocode o) {
                        super.onPostExecute(o);
                        Snackbar.make(view, "Geocode : " + o, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }.execute();
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
