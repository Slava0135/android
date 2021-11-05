package com.example.myapplication

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BackstackTest {

    @get:Rule
    val mainActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testBackstack1() {
        launchActivity<MainActivity>()
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToFirst))
            .perform(ViewActions.click())
        Espresso.pressBackUnconditionally()
        assertTrue(mainActivityRule.scenario.state == Lifecycle.State.DESTROYED)
    }

    @Test
    fun testBackstack2() {
        launchActivity<MainActivity>()
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToFirst))
            .perform(ViewActions.click())
        Espresso.pressBackUnconditionally()
        assertTrue(mainActivityRule.scenario.state == Lifecycle.State.DESTROYED)
    }

    @Test
    fun testBackstack3() {
        launchActivity<MainActivity>()
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertTrue(mainActivityRule.scenario.state == Lifecycle.State.DESTROYED)
    }

    @Test
    fun testBackstackSequence() {
        launchActivity<MainActivity>()
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird))
            .perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testBackstackWithAbout() {
        launchActivity<MainActivity>()
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird))
            .perform(ViewActions.click())
        openAbout()
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}