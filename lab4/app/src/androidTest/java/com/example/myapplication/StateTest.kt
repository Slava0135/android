package com.example.myapplication

import android.content.pm.ActivityInfo
import android.os.SystemClock.sleep
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class StateTest {

    @get:Rule
    val mainActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testState1() {
        rotateScreen()
        sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testState2() {
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        rotateScreen()
        Espresso.onView(ViewMatchers.withId(R.id.bnToFirst))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testState3() {
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird))
            .perform(ViewActions.click())
        rotateScreen()
        Espresso.onView(ViewMatchers.withId(R.id.bnToFirst))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun rotateScreen() {
        mainActivityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        sleep(1000)
        mainActivityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        sleep(1000)
    }
}