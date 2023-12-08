package com.example.android_final_project.application.util.alarms;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.android_final_project.R;
import com.example.android_final_project.application.MainApplication;

public class ReminderBroadcastReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "reminder_channel_id";

    @Override
    public void onReceive(Context context, Intent intent) {

        String taskName = intent.getStringExtra("TASK_NAME");
        Log.d(MainApplication.LOG_HEADER, "RECEIVING BROADCAST for " + taskName);
        String taskDescription = intent.getStringExtra("TASK_DESCRIPTION");
        Log.d(MainApplication.LOG_HEADER, "RECEIVING BROADCAST for description " + taskDescription);

        int taskId = intent.getIntExtra("TASK_ID", -1);
        Log.d(MainApplication.LOG_HEADER, "RECEIVING BROADCAST for task ID" + taskId);


        // Trigger the notification
        if(taskId != -1) {

            triggerNotification(context, taskName, taskDescription, taskId);
        }
    }

    public static void setupReminderChannel(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Reminder Channel";
            String description = "Channel for Task Reminders";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            Log.d(MainApplication.LOG_HEADER, "Registering / creating notification channel");

        }
    }

    private void triggerNotification(Context context, String taskName, String taskDescription, int taskId) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create a notification channel if necessary (required for Android O and above)

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(taskName)
                .setContentText(taskDescription)
                .setSmallIcon(R.drawable.ic_reminder)
                .build();

        Log.d(MainApplication.LOG_HEADER, "TRIGGERING NOTIFICATION for: " + taskName);

        notificationManager.notify(taskId, notification); // Use task ID as unique notification ID
    }
}