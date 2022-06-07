package com.software.software2.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class UserModel {

    private String userId;
    private String displayName;

    public UserModel(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

}