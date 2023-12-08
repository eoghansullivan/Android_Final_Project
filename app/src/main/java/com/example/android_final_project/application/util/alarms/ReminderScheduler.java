package com.example.android_final_project.application.util.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;

import com.example.android_final_project.application.MainApplication;
import com.example.android_final_project.db.Task;
import com.example.android_final_project.db.TaskViewModel;

public class ReminderScheduler {

    public static void scheduleReminder(Context context, Task task) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderBroadcastReceiver.class);
        intent.putExtra("TASK_NAME", task.getName());
        intent.putExtra("TASK_DESCRIPTION", task.getDescription());
        intent.putExtra("TASK_ID", task.getId());

        Log.d(MainApplication.LOG_HEADER, "ALARM SCHEDULE FOR: " + task);


        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, task.getId(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager != null && alarmManager.canScheduleExactAlarms()) {
                Log.d(MainApplication.LOG_HEADER, "scheduling alarm on newer android");

                alarmManager.setExact(AlarmManager.RTC_WAKEUP, task.getDueDateAsDate().getTime(), pendingIntent);
            } else {
                Log.d(MainApplication.LOG_HEADER, "App does not have permission to schedule alarm");

            }
        } else {
            Log.d(MainApplication.LOG_HEADER, "scheduling alarm");

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, task.getDueDateAsDate().getTime(), pendingIntent);
        }
    }

    public static void synchroniseReminders(Context context, TaskViewModel taskViewModel, LifecycleOwner owner) {
        Log.d(MainApplication.LOG_HEADER, "Synchronising reminders");

        taskViewModel.getAllTasks().observe(owner, tasks -> {
            for (Task task : tasks) {
                if (task.isAlarmOn()) {
                    if(task.getDueDateAsDate().getTime() > System.currentTimeMillis()) {
                        Log.d(MainApplication.LOG_HEADER, "Task to be synced: " + task.getName());
                        ReminderScheduler.scheduleReminder(context, task);
                    }
                }
            }
        });
    }
}
