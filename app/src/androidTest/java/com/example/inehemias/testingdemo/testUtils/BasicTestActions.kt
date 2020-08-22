package com.example.inehemias.testingdemo.testUtils

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
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
    fun getCurrentActivity(): Activity? {
        val currentActivity = arrayOf<Activity?>(null)
        getInstrumentation().runOnMainSync(Runnable {
            val resumedActivities: Collection<Activity> =
                ActivityLifecycleMonitorRegistry.getInstance()
                    .getActivitiesInStage(Stage.RESUMED)
            if (resumedActivities.iterator().hasNext()) {
                currentActivity[0] = resumedActivities.iterator().next() as Activity?
            }
        })
        return currentActivity[0]
    }
}
