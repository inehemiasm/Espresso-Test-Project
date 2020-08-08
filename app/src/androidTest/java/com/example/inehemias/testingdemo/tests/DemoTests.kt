package com.example.inehemias.testingdemo.tests

import androidx.test.core.app.ActivityScenario
import com.example.inehemias.testingdemo.pages.DemoPage
import com.example.inehemias.testingdemo.testUtils.LoadTestData.getLocalizedProperty
import com.example.inehemias.testingdemo.testUtils.RunnerSetup
import com.example.inehemias.testingdemo.testUtils.TestTags
import com.example.inehemias.testingdemo.testUtils.UiTestSetup
import com.example.inehemias.testingdemo.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber

@RunWith(RunnerSetup::class)
class DemoTests : DemoPage() {
    private val locale by lazy { UiTestSetup.locale }
    private val testTag by lazy { UiTestSetup.tags[0] }

    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var localizedText: String

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
    @TestTags(runTags = ["Regression", "SMOKE_BR", "SMOKE_DE"])
    fun verifyThisTestWillRunForUS() {
        waitForPageToLoad(2)
        val englishText = "Welcome to my app"
        Timber.e("Running $testTag tests for $locale ")
        addNewWord(englishText)
        verifyWordAppearsOnScreen(localizedText)
        waitForPageToLoad(3)
    }

    @Test
    @TestTags(runTags = ["SMOKE_BR"])
    fun verifyThisTestWillRunForBrazil() {
        Timber.e("Running $testTag tests for $locale ")
        waitForPageToLoad(2)
        val portugueseText = "Bem vindo ao meu aplicativo"
        addNewWord(portugueseText)
        verifyWordAppearsOnScreen(localizedText)
        waitForPageToLoad(3)
    }

    @Test
    @TestTags(runTags = ["SMOKE_DE"])
    fun verifyThisTestWillRunForDE() {
        Timber.e("Running $testTag tests for $locale ")
        waitForPageToLoad(2)
        val deutschText = "Welkom bij mijn app"
        addNewWord(deutschText)
        verifyWordAppearsOnScreen(localizedText)
        waitForPageToLoad(3)
    }
}
