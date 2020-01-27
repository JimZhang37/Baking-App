package com.example.bakingapp.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.bakingapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class ActivityMainSimpleTest {

    private static final int ITEM_NUMBER = 3;
    @Rule public ActivityTestRule<ActivityMain> mActivityTestRule =
            new ActivityTestRule<>(ActivityMain.class);

    @Test
    public void clickRecyclerViewListItem_OpenActivityRecipe(){
//        onView(ViewMatchers.withId(R.id.recycler_recipe_list))
//                .perform(RecyclerViewActions.scrollToHolder())

        onView(ViewMatchers.withId(R.id.recycler_recipe_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_NUMBER, click()));
        onView(ViewMatchers.withId(R.id.tv_ingredients)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.tv_ingredients)).check(matches(withSubstring("INGREDIENTS:")));
    }
}
