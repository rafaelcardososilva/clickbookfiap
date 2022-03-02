package br.com.clickbook

import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.*

import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loginTest() {
        Thread.sleep(500)

        onView(withId(R.id.etEmailLogin)).perform(replaceText("usuario@gmail.com"))

        onView(withId(R.id.etPasswordLogin)).perform(replaceText("123456"), closeSoftKeyboard())

        onView(withId(R.id.btLogin)).perform(click())

        Thread.sleep(5000)

        onView(withId(R.id.tvTitleFeed)).check(ViewAssertions.matches(isDisplayed()))
    }

    @After
    fun logout() {
        Thread.sleep(1000)
        onView(withId(R.id.profileFragmentNav)).perform(click())
        onView(withId(R.id.btSignOut)).perform(click())
    }
}
