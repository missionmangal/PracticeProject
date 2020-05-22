package com.myapplication.espresso_testing;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.myapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;

public class ListActivityTest {

    IdlingResource mIdlResoruce;
    @Rule
    public ActivityTestRule<ListActivity> activityActivityTestRule = new ActivityTestRule(ListActivity.class);

    @Before
    public void setUp() throws Exception{
        ActivityScenario<ListActivity> scenario = ActivityScenario.launch(ListActivity.class);
        scenario.onActivity(new ActivityScenario.ActivityAction<ListActivity>() {
            @Override
            public void perform(ListActivity activity) {
                mIdlResoruce = activity.getMIdleResource();
                IdlingRegistry.getInstance().register(mIdlResoruce);
            }
        });

    }

    @After
    public void tearUp() throws Exception{
        if (mIdlResoruce != null)
            IdlingRegistry.getInstance().unregister(mIdlResoruce);


    }

    @Test
    public void testRecyclerViewClick(){
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
    }
}
