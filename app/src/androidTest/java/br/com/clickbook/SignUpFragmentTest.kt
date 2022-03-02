package br.com.clickbook


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SignUpFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun signUpTest() {
        val number = (0..100).random()
        val letter = ('a'..'z').random()
        val email = "teste_${letter}_${number}@gmail.com"

        Thread.sleep(500)

        onView(withId(R.id.tvNewAccount)).perform(click())

        onView(withId(R.id.etNameSignUp)).perform(replaceText("Teste"))

        onView(withId(R.id.etEmailSignUp)).perform(replaceText(email))

        onView(withId(R.id.etPasswordSignUp)).perform(replaceText("123456"))

        onView(withId(R.id.etPasswordAgainSignUp)).perform(replaceText("123456"))

        onView(withId(R.id.btSignUp)).perform(click(), closeSoftKeyboard())

        Thread.sleep(2000)

        onView(withId(R.id.tvTitleFeed)).check(ViewAssertions.matches(isDisplayed()))
    }

    @After
    fun logout() {
        Thread.sleep(1000)
        onView(withId(R.id.profileFragmentNav)).perform(click())
        onView(withId(R.id.btSignOut)).perform(click())
    }
}
