package fr.ekito.myweatherapp;

import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.ekito.myweatherlibrary.json.weather.Forecastday_;

/**
 * Created by arnaud on 04/08/2016.
 */
public class WeatherFormat {
    public static List<Forecastday_> filterForecast(List<Forecastday_> forecastday) {
        List<Forecastday_> filtered = new ArrayList<>();
        System.out.println("");
        for (Forecastday_ f : forecastday) {
            if (!f.getIcon().startsWith("nt_")) {
                filtered.add(f);
                if (filtered.size() >= 4) {
                    break;
                }
            }
        }
        return filtered;
    }

    @NonNull
    public static String getTemp(Forecastday_ day1) {
        return day1.getLow().getCelsius() + " - " + day1.getHigh().getCelsius();
    }

    public static void displayWeatherIcon(TextView txt, int size, Forecastday_ f) {
        txt.setTypeface(MainApplication.getClimaconFont());
        txt.setText(getWeatherCode(f.getIcon()));
        txt.setTextSize(size);
    }

    public static String getWeatherCode(String icon) {
        icon = icon.replaceAll("nt_", "");
        if (icon.equals("cloudy")) {
            return Character.toString((char) 0x049);
        } else if (icon.equals("partlycloudy")) {
            return Character.toString((char) 0x022);
        } else
            return Character.toString((char) 0x049);
    }
}
