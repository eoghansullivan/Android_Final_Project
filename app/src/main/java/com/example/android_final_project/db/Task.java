package com.example.android_final_project.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private TaskType taskType; // Updated field name
    private String dueDate; // Store due date as a string in the database

    // Transient field to convert between String and Date
    @Ignore
    private Date dueDateAsDate;

    public enum TaskType {
        PERSONAL,
        WORK,
        LEISURE,
        UNDEFINED
    }

    // Constructors
    public Task() {
        // Default constructor
    }

    public Task(String name, String description, TaskType taskType, String dueDate) {
        this.name = name;
        this.description = description;
        this.taskType = taskType;
        this.dueDate = dueDate; // Store due date as a string in the database
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

    // Get and set methods for dueDateAsDate
    public Date getDueDateAsDate() {
        if (dueDateAsDate == null) {
            dueDateAsDate = stringToDate(dueDate); // Convert String to Date
        }
        return dueDateAsDate;
    }

    public void setDueDateAsDate(Date dueDateAsDate) {
        this.dueDateAsDate = dueDateAsDate;
        this.dueDate = dateToString(dueDateAsDate); // Convert Date to String
    }

    // Helper methods for converting Date to String and vice versa
    private String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    private Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}