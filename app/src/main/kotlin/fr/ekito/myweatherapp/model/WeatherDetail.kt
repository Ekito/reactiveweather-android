package fr.ekito.myweatherapp.model

import io.realm.RealmObject

/**
 * Created by arnaud on 19/10/2016.
 */
open class WeatherDetail constructor(var title: String = "", var icon: String = "", var temp: String = "") : RealmObject() {
}