package com.example.android_final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;

import com.example.android_final_project.application.util.alarms.ReminderBroadcastReceiver;
import com.example.android_final_project.application.util.alarms.ReminderScheduler;
import com.example.android_final_project.db.TaskViewModel;
import com.example.android_final_project.db.TaskViewModelFactory;
import com.example.android_final_project.gui.AddTaskFragment;
import com.example.android_final_project.gui.TaskListFragment;


public class MainActivityFrame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);
        ReminderBroadcastReceiver.setupReminderChannel(this);


        TaskViewModelFactory factory = new TaskViewModelFactory(getApplication());
        TaskViewModel taskViewModel = new ViewModelProvider(this, factory).get(TaskViewModel.class);


        Button addTask = findViewById(R.id.addTaskButton);
        Button viewTasks = findViewById(R.id.viewTasksButton);
        addTask.setOnClickListener(v -> addTask());
        viewTasks.setOnClickListener(v -> viewTasks());

        ReminderScheduler.synchroniseReminders(this, taskViewModel, this);
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