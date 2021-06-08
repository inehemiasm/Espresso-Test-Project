package com.example.inehemias.testingdemo.tests

import android.Manifest
import androidx.test.rule.GrantPermissionRule
import com.example.inehemias.testingdemo.pages.DemoPage
import com.example.inehemias.testingdemo.testUtils.LoadTestData.getLocalizedProperty
import com.example.inehemias.testingdemo.testUtils.RunnerSetup
import com.example.inehemias.testingdemo.testUtils.TestTags
import com.example.inehemias.testingdemo.testUtils.UiTestSetup
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber

@RunWith(RunnerSetup::class)
class DemoTests : DemoPage() {
    private val locale by lazy { UiTestSetup.locale }

    private lateinit var localizedText: String

    @get:Rule
    val mRuntimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @Before
    fun initializeVariables() {
        localizedText = getLocalizedProperty("welcome")
    }

    @Test
    @TestTags(runTags = ["Regression"])
    fun verifySecondNoteForUS() {
        Timber.d("Running tests for $locale")
        waitForPageToLoad(2)
        val englishText = "Welcome to my app"
        addNewWord(englishText)
        verifyWordAppearsOnScreen(localizedText)
        waitForPageToLoad(3) // Just to keep the screen open
    }

    @Test
    @TestTags(runTags = ["SMOKE_BR"])
    fun verifySecondNoteForBrazil() {
        Timber.d("Running tests for $locale ")
        waitForPageToLoad(2)
        val portugueseText = "Bem vindo ao meu aplicativo"
        addNewWord(portugueseText)
        verifyWordAppearsOnScreen(localizedText)
        waitForPageToLoad(3) // Just to keep the screen open
    }

    @Test
    @TestTags(runTags = ["SMOKE_DE"])
    fun verifySecondNoteRunForDE() {
        Timber.d("Running tests for $locale ")
        waitForPageToLoad(2)
        val deutschText = "Welkom bij mijn app"
        addNewWord(deutschText)
        verifyWordAppearsOnScreen(localizedText)
        waitForPageToLoad(3) // Just to keep the screen open
    }
}
