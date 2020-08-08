package com.example.inehemias.testingdemo.testUtils

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import java.lang.StringBuilder
import org.hamcrest.Matcher
import timber.log.Timber

open class BasicTestActions {

    fun waitForPageToLoad(seconds: Int) {
        var seconds = if (seconds < 0) 0 else seconds
        Timber.w("Waiting $seconds Seconds for page to finish loading....")
        while (--seconds >= 0) {
            try {
                Thread.sleep(1000)
            } catch (t: Throwable) {
            }
        }
    }
    fun getElementText(matcher: Matcher<View>): String {
        var stringHolder = StringBuilder()
        Espresso.onView(matcher).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView
                stringHolder.append(tv.text)
            }
        })
        return stringHolder.toString()
    }
}
