package com.example.android_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android_final_project.gui.AddTaskFragment;

public class MainActivityFrame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        // Add the fragment to the 'fragment_container' FrameLayout
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainFrameLayout, new AddTaskFragment()).commit();
        }
    }


}