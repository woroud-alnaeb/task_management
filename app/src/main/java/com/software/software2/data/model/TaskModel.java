package com.software.software2.data.model;

public class TaskModel {

    String title;
    String description;
    boolean isCompleted;
    String userID;

    TaskModel(String title, String description,boolean isCompleted, String userID){
        this.title= title;
        this.description= description;
        this.isCompleted= isCompleted;
        this.userID= userID;


    }

}
