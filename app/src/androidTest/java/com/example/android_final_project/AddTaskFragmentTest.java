package com.example.android_final_project;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.android_final_project.gui.AddTaskFragment;
import com.example.android_final_project.util.ToastMatcher;
import com.example.android_final_project.old_project_package.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AddTaskFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testTaskFormInput() {
        // Set up the scenario to launch the fragment
        FragmentScenario<AddTaskFragment> scenario = FragmentScenario.launchInContainer(AddTaskFragment.class);


        // Enter text in the task name field
        onView(withId(R.id.taskNameEt)).perform(typeText("New Task"), closeSoftKeyboard());

        // Enter text in the description field
        onView(withId(R.id.taskDescriptionEt)).perform(typeText("Task Description"), closeSoftKeyboard());

        // Perform a click on the save button
        onView(withId(R.id.saveButton)).perform(click());

        // Check for the Toast (or Snackbar) text
        onView(withText("Task added successfully")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }
}