package com.myapplication.espresso_testing;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import com.myapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
//@LargeTest
//@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =new  ActivityTestRule(LoginActivity.class,true);

    @Before
    public void setUp() throws Exception{
        ActivityScenario activityScenario = ActivityScenario.launch(LoginActivity.class);
        activityScenario.onActivity(new ActivityScenario.ActivityAction<LoginActivity>() {
            @Override
            public void perform(LoginActivity activity) {
                mIdlingResource = activity.getMIdlingResource();
                // To prove that the test fails, omit this call:
                IdlingRegistry.getInstance().register(mIdlingResource);
            }
        });
    }
    @After
    public void tearDown() throws Exception {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

    @Test
    public void loginTest(){

//        Espresso.onView(withId(R.id.username)).perform(ViewActions.typeText("Vishnu"));
//        Espresso.onView(withId(R.id.password)).perform(ViewActions.typeText("123456"));
//        Espresso.onView(withId(R.id.login)).perform(ViewActions.click(),ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.username)).perform(ViewActions.typeText("Vishnu"));
        Espresso.onView(withId(R.id.password)).perform(ViewActions.typeText("123456"));
        Espresso.onView(withId(R.id.password)).perform(ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.login)).perform(ViewActions.click());

    }
}