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

    private Button addTask, viewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        addTask = findViewById(R.id.addTaskButton);
        viewTasks = findViewById(R.id.viewTasksButton);
        
        addTask.setOnClickListener(v -> addTask());
        viewTasks.setOnClickListener(v -> viewTasks());
//        // Add the fragment to the 'fragment_container' FrameLayout
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.mainFrameLayout, new AddTaskFragment()).commit();
//        }
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


        // If you need to pass data to the fragment, use fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentFrame, fragment);
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.addToBackStack(null); // Enables back navigation
        fragmentTransaction.commit();
    }

}