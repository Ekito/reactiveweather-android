package fr.ekito.myweatherapp

import android.app.Activity
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout

/**
 * Created by arnaud on 19/10/2016.
 */
object DialogHelper {
    /**
     * ask location dialog
     */
    fun locationDialog(view: View, callback: WeatherCallback) {
        val input = EditText(callback as Activity)
        input.hint = "i.e: Paris, France"

        val builder = AlertDialog.Builder(view.context)
        builder.setMessage(R.string.location_title).setPositiveButton(R.string.search) { dialog, id ->
            dialog.dismiss()
            callback.getWeatherData(view, input.text.toString())
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
}