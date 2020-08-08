package com.example.inehemias.testingdemo.pages

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.inehemias.testingdemo.R
import com.example.inehemias.testingdemo.testUtils.BasicTestActions

open class DemoPage : BasicTestActions() {

    fun addNewWord(word: String) {
        val fabButton = onView(ViewMatchers.withId(R.id.fab))
        fabButton.perform(click())
        val addWordEditText = onView(ViewMatchers.withId(R.id.edit_word))
        addWordEditText.perform(replaceText(word))
        waitForPageToLoad(1)
        onView(withId(R.id.button_save)).perform(click())
    }

    fun verifyWordAppearsOnScreen(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

}