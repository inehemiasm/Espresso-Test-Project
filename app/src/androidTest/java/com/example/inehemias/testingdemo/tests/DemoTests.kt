package com.example.inehemias.testingdemo.tests

import android.Manifest
import androidx.test.core.app.ActivityScenario
import androidx.test.rule.GrantPermissionRule
import com.example.inehemias.testingdemo.pages.DemoPage
import com.example.inehemias.testingdemo.testUtils.LoadTestData.getLocalizedProperty
import com.example.inehemias.testingdemo.testUtils.RunnerSetup
import com.example.inehemias.testingdemo.testUtils.TestTags
import com.example.inehemias.testingdemo.testUtils.UiTestSetup
import com.example.inehemias.testingdemo.ui.MainActivity
import com.squareup.spoon.Spoon
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber


@RunWith(RunnerSetup::class)
class DemoTests : DemoPage() {
    private val locale by lazy { UiTestSetup.locale }

    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var localizedText: String

    @get:Rule
    val mRuntimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @Before
    fun initializeVariables() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        localizedText = getLocalizedProperty("welcome")
    }
    @After
    fun cleanUp() {
        activityScenario.close()
    }

    @Test
    @TestTags(runTags = ["Regression"])
    fun verifyThisTestWillRunForUS() {
        Timber.d("Running tests for $locale ")
        waitForPageToLoad(2)
        val englishText = "Welcome to my app"
        addNewWord(englishText)
        verifyWordAppearsOnScreen(localizedText)
        Spoon.screenshot(getCurrentActivity(), "Saved")
        waitForPageToLoad(3) // Just to keep the screen open
    }

    @Test
    @TestTags(runTags = ["SMOKE_BR"])
    fun verifyThisTestWillRunForBrazil() {
        Timber.d("Running tests for $locale ")
        waitForPageToLoad(2)
        Spoon.screenshot(getCurrentActivity(), "After")
        val portugueseText = "Bem vindo ao meu aplicativo"
        addNewWord(portugueseText)
        verifyWordAppearsOnScreen(localizedText)
        Spoon.screenshot(getCurrentActivity(), "Saved")
        waitForPageToLoad(3) // Just to keep the screen open
    }

    @Test
    @TestTags(runTags = ["SMOKE_DE"])
    fun verifyThisTestWillRunForDE() {
        Timber.d("Running tests for $locale ")
        waitForPageToLoad(2)
        val deutschText = "Welkom bij mijn app"
        addNewWord(deutschText)
        verifyWordAppearsOnScreen(localizedText)
        Spoon.screenshot(getCurrentActivity(), "Saved")
        waitForPageToLoad(3) // Just to keep the screen open
    }
}
