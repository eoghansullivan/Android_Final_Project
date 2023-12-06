package com.example.android_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android_final_project.gui.TaskListFragment;

public class TestOutputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_output);

        // Add the fragment to the 'fragment_container' FrameLayout
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.testFrame, new TaskListFragment()).commit();
        }
    }
}