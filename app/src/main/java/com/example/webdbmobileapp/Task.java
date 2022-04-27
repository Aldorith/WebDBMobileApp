package com.example.webdbmobileapp;

public class Task {
    String taskID;
    String toDoTitle;
    String toDoDescription;

    public Task() { }
    public Task(String taskID, String ToDoTitle, String ToDoDescription) {
        this.taskID = taskID;
        this.toDoTitle = ToDoTitle;
        this.toDoDescription = ToDoDescription;
    }

    public String getTaskID () {
        return taskID;
    }

    public String getToDoTitle () {
        return toDoTitle;
    }

    public String getToDoDescription () {
        return  toDoDescription;
    }
}
