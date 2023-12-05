package com.example.android_final_project.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private TaskType taskType; // Updated field name
    private Date dueDate; // Represents the due date

    public enum TaskType {
        PERSONAL,
        WORK,
        LEISURE
    }

    // Constructors
    public Task() {
        // Default constructor
    }

    public Task(String name, String description, TaskType taskType, Date dueDate) {
        this.name = name;
        this.description = description;
        this.taskType = taskType;
        this.dueDate = dueDate;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}