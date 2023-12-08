package com.example.android_final_project.db;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.android_final_project.application.MainApplication;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private TaskType taskType;
    private String dueDate;

    private boolean completed;

    private boolean alarmOn;
    // Transient field to convert between String and Date
    @Ignore
    private Date dueDateAsDate;
    @Ignore
    public static final String INITIAL_DATE_TIME = "0000-00-00 00:00:00";

    public enum TaskType {
        LEISURE("LEISURE"),
        PERSONAL("PERSONAL"),
        WORK("WORK"),
        UNDEFINED("UNDEFINED");

        private final String description;

        public static TaskType fromDescription(String description) {
            for (TaskType taskType : TaskType.values()) {
                if (taskType.description.equalsIgnoreCase(description)) {
                    return taskType;
                }
            }
            throw new IllegalArgumentException("No enum constant with description: " + description);
        }

        TaskType(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }


    // Constructors
    @Ignore
    public Task() {
        // Default constructor
    }

    @Ignore
    public Task(String name, String description, TaskType taskType, String dueDate,boolean alarmOn, int id){
        this(name, description, taskType, dueDate, alarmOn);
        this.id = id;
    }

    public Task(String name, String description, TaskType taskType, String dueDate, boolean alarmOn) {
        this.name = name;
        this.description = description;
        this.taskType = taskType;
        this.dueDate = dueDate;
        this.completed = false;
        this.alarmOn = alarmOn;
        Log.d(MainApplication.LOG_HEADER, "created: " + this);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate; // Store due date as a string in the database
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }

    public boolean isCompleted(){
        return this.completed;
    }

    // Get and set methods for dueDateAsDate
    public Date getDueDateAsDate() {
        return stringToDate(dueDate);
    }

    // Helper methods for converting Date to String and vice versa
    private String dateToString(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    private Date stringToDate(String dateString) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }

    public void setAlarmOn(boolean alarmOn) {
        this.alarmOn = alarmOn;
    }

    public void setDueDateAsDate(Date dueDateAsDate) {
        this.dueDateAsDate = dueDateAsDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", taskType=" + taskType +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}