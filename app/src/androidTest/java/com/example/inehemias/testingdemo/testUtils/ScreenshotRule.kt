package com.example.inehemias.testingdemo.testUtils

import android.graphics.Bitmap
import android.os.Environment.getExternalStorageDirectory
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor
import androidx.test.runner.screenshot.ScreenCaptureProcessor
import androidx.test.runner.screenshot.Screenshot
import androidx.test.uiautomator.UiDevice
import com.example.inehemias.testingdemo.ui.MainActivity
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.io.File
import java.io.IOException

class ScreenshotRule : TestWatcher() {
    private val mainActivity  = MainActivity::class.java
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    private lateinit var description: Description
    private lateinit var filePath: File
    override fun starting(description: Description?) {
        super.starting(description)

        activityScenario = launch(mainActivity)
        this.description = description!!
    }

    override fun succeeded(description: Description?) {
        super.succeeded(description)
        activityScenario.close()
    }
    override fun failed(t: Throwable?, description: Description) {
        super.failed(t, description)
        takeScreenshot("Failed")
        activityScenario.close()
    }
    fun takeScreenshot(message: String) {
        try {
            filePath = obtainDirectory(description.className, description.methodName)
            val device = UiDevice.getInstance(getInstrumentation())
            val filename = description.methodName + message + IMAGE_EXTENSION
            device.takeScreenshot(File(filePath, filename))
        } catch (e: Throwable) {
            // Note: This will not be attached to spoon tests results
            // as we cannot override the path to match spoon directory
            captureScreenshot()
        }
    }
    /**
     * An alternative way to capture screenshot.
     * Please note that images taken by this method will not be attached to test results
     * Unable to modify root directory
     */
    private fun captureScreenshot() {
        val filename = description.methodName
        val capture = Screenshot.capture()
        capture.format = Bitmap.CompressFormat.PNG
        capture.name = filename
        val processors = HashSet<ScreenCaptureProcessor>()
        processors.add(BasicScreenCaptureProcessor())
        try {
            capture.process(processors)
        } catch (e: IOException) {
            throw IllegalStateException(e)
        }
    }
    /**
     * Method to generate needed directories for to save screenshots
     * @return File path
     */
    private fun obtainDirectory(
        testClassName: String,
        testMethodName: String
    ): File {
        val directory = File(
            getExternalStorageDirectory(),
            SPOON_DIRECTORY
        )
        val dirClass = File(directory, testClassName)
        val dirMethod = File(dirClass, testMethodName)
        createDir(dirMethod)
        return dirMethod
    }
    private fun createDir(dir: File) {
        val parent = dir.parentFile
        if (!parent.exists()) {
            createDir(parent)
            dir.setWritable(true)
            dir.setReadable(true)
            dir.setExecutable(true)
        }
        if (!dir.exists() && !dir.mkdirs()) {
            throw RuntimeException("Unable to create output dir: " + dir.absolutePath)
        }
    }
    companion object {
        private const val SPOON_DIRECTORY = "app_spoon-screenshots"
        private const val IMAGE_EXTENSION = ".png"
    }
}