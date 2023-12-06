package com.example.android_final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import com.example.android_final_project.gui.AddTaskFragment;
import com.example.android_final_project.gui.TaskListFragment;

public class MainActivityFrame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);
        Button addTask = findViewById(R.id.addTaskButton);
        Button viewTasks = findViewById(R.id.viewTasksButton);
        addTask.setOnClickListener(v -> addTask());
        viewTasks.setOnClickListener(v -> viewTasks());
    }

    private void viewTasks() {

        Fragment taskListFrag = new TaskListFragment();
        showFragment(taskListFrag);
    }

    private void addTask() {
        Fragment addTaskFrag = new AddTaskFragment();
        showFragment(addTaskFrag);
    }

    public void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentFrame, fragment);
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.addToBackStack(null); // Enables back navigation
        fragmentTransaction.commit();
    }

}