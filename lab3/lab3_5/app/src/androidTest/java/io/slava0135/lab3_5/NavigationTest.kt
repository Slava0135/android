package io.slava0135.lab3_5

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val mainActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testFragment1Navigation() {
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testFragment2Navigation() {
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToFirst))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testFragment3Navigation() {
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToFirst))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testAboutNavigation() {
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(R.id.bnToThird))
            .perform(ViewActions.click())
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testUpNavigation() {
        openAbout()
        navigateUp()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond))
            .perform(ViewActions.click())
        openAbout()
        navigateUp()
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.bnToThird))
            .perform(ViewActions.click())
        openAbout()
        navigateUp()
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        navigateUp()
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        navigateUp()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun navigateUp() {
        Espresso.onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
    }
}