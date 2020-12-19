package com.example.inehemias.testingdemo.tests

import android.Manifest
import androidx.test.rule.GrantPermissionRule
import com.example.inehemias.testingdemo.pages.DemoPage
import com.example.inehemias.testingdemo.testUtils.LoadTestData
import com.example.inehemias.testingdemo.testUtils.RunnerSetup
import com.example.inehemias.testingdemo.testUtils.TestTags
import com.example.inehemias.testingdemo.testUtils.UiTestSetup
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber

@RunWith(RunnerSetup::class)
class SecondDemoTests : DemoPage() {

    private val locale by lazy { UiTestSetup.locale }
    private lateinit var localizedText: String

    @get:Rule
    val mRuntimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @Before
    fun initializeVariables() {
        localizedText = LoadTestData.getLocalizedProperty("second_note")
    }

    @Test
    @TestTags(runTags = ["Regression"])
    fun verifyThisTestWillRunForUS() {
        Timber.d("Running tests for $locale")
        waitForPageToLoad(2)
        val englishText = "This is a second Note!!!"
        addNewWord(englishText)
        verifyWordAppearsOnScreen(localizedText)
        waitForPageToLoad(3) // Just to keep the screen open
    }

    @Test
    @TestTags(runTags = ["SMOKE_BR"])
    fun verifyThisTestWillRunForBrazil() {
        Timber.d("Running tests for $locale ")
        waitForPageToLoad(2)
        val portugueseText = "Esta e uma segunda nota!!!"
        addNewWord(portugueseText)
        verifyWordAppearsOnScreen(localizedText)
        waitForPageToLoad(3) // Just to keep the screen open
    }

    @Test
    @TestTags(runTags = ["SMOKE_DE"])
    fun verifyThisTestWillRunForDE() {
        Timber.d("Running tests for $locale ")
        waitForPageToLoad(2)
        val deutschText = "Dit is een tweede notitie!!!"
        addNewWord(deutschText)
        verifyWordAppearsOnScreen(localizedText)
        waitForPageToLoad(3) // Just to keep the screen open
    }
}
