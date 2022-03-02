package br.com.clickbook


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun login() {
        Thread.sleep(1000)
        onView(withId(R.id.etEmailLogin)).perform(replaceText("usuario@gmail.com"))
        onView(withId(R.id.etPasswordLogin)).perform(replaceText("123456"), closeSoftKeyboard())
        onView(withId(R.id.btLogin)).perform(click())
        Thread.sleep(4000)
    }

    @Test
    fun logoutTest() {
        Thread.sleep(500)

        onView(withId(R.id.profileFragmentNav)).perform(click())

        onView(withId(R.id.btSignOut)).perform(click())

        onView(withId(R.id.titleLogin)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun verifyEmailTest() {
        Thread.sleep(500)

        onView(withId(R.id.profileFragmentNav)).perform(click())

        onView(withId(R.id.tvEmailProfile)).check(ViewAssertions.matches(isDisplayed()))

        onView(withId(R.id.btSignOut)).perform(click())
    }
}
