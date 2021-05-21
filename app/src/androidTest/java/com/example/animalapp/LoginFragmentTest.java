package com.example.animalapp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.fragment.app.Fragment;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class LoginFragmentTest<L extends Fragment> {
    // To launch the mentioned activity under testing
    @Rule
    public LoginFragment<LoginFragment> MethodRule = new LoginFragment<>(LoginFragment.class);

    @Test
    public void testHintVisibility(){
        // check hint visibility
        onView(withId(R.id.edittext_username)).check(matches(withHint("Username")));
        // enter name
        onView(withId(R.id.edittext_username)).perform(typeText("testuser"),closeSoftKeyboard());
        onView(withId(R.id.edittext_username)).check(matches(withText("testuser")));
    }

    @Test
    public void testUserPassword(){
        // check hint visibility
        onView(withId(R.id.edittext_password)).check(matches(withHint("Password")));
        // enter name
        onView(withId(R.id.edittext_password)).perform(typeText("test123"),closeSoftKeyboard());
        onView(withId(R.id.edittext_password)).check(matches(withText("test123")));
    }

    @Test
    public void testButtonClick(){
        // enter name`
        onView(withId(R.id.button_login)).perform(typeText("testuser"),closeSoftKeyboard());
        // clear text
        onView(withText("Clear")).perform(click());
        // check hint visibility after the text is cleared
        onView(withId(R.id.button_login)).check(matches(withHint("Username")));
    }
}

//This test is giving me errors but im unsure as to why this is erroring asw its not telling me when i run it