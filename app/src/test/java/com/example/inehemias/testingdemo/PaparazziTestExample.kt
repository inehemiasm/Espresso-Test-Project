package com.example.inehemias.testingdemo

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class PaparazziTestExample {
    @get:Rule
    var paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_3)

    @Test
    fun pixel3() {
        val addWordLayout = paparazzi.inflate<LinearLayout>(R.layout.activity_new_word)
        val text = addWordLayout.findViewById<TextView>(R.id.edit_word)
        text.text = "Testing Demo"
        text.setBackgroundColor(Color.GRAY)
        paparazzi.snapshot(addWordLayout, "First Shot")

        text.text = "Second Screenshot"
        paparazzi.snapshot(addWordLayout, "Second Shot")
    }

    @Test
    fun listOfWords() {
        val mainActivityLayout = paparazzi.inflate<View>(R.layout.activity_main)
        val reciclerView = mainActivityLayout.findViewById<View>(R.id.recyclerview)
        paparazzi.snapshot(mainActivityLayout, "Main Layout")
    }

}