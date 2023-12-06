package com.example.android_final_project.application.util;

import android.util.Log;

public class CustomLogger {
    private static final String TAG = "A00287845"; // Customize your tag

    public static void d(String... messages) {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];
        String methodName = stackTrace.getMethodName();
        String className = stackTrace.getClassName();

        StringBuilder logMessage = new StringBuilder(className)
                .append(".")
                .append(methodName)
                .append(": ");

        for (String message : messages) {
            logMessage.append(message).append(" ");
        }

        Log.d(TAG, logMessage.toString().trim());
    }

    public static void e(String... messages) {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];
        String methodName = stackTrace.getMethodName();
        String className = stackTrace.getClassName();

        StringBuilder logMessage = new StringBuilder(className)
                .append(".")
                .append(methodName)
                .append(": ");

        for (String message : messages) {
            logMessage.append(message).append(" ");
        }

        Log.e(TAG, logMessage.toString().trim());
    }

    public static void e(Throwable throwable, String... messages) {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[4];
        String methodName = stackTrace.getMethodName();
        String className = stackTrace.getClassName();

        StringBuilder logMessage = new StringBuilder(className)
                .append(".")
                .append(methodName)
                .append(": ");

        for (String message : messages) {
            logMessage.append(message).append(" ");
        }

        Log.e(TAG, logMessage.toString().trim(), throwable);
    }


    // Add similar methods for other log levels (e.g., i, w, v)
}