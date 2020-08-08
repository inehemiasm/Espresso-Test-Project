package com.example.inehemias.testingdemo.testUtils

import androidx.test.platform.app.InstrumentationRegistry
import java.io.IOException
import java.util.Properties
import timber.log.Timber

object LoadTestData {

    fun getLocalizedProperty(propKey: String): String {
        val localizedFile = UiTestSetup.locale + "_file.properties"
        val props = Properties()
        try {
            InstrumentationRegistry.getInstrumentation().context.resources
                .assets.open(localizedFile).use { inputStream -> props.load(inputStream) }
        } catch (ex: IOException) {
            Timber.e(ex)
        }
        val secret = props.getProperty(propKey)
        return secret ?: ""
    }
}
