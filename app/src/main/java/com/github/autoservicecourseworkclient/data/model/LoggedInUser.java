package com.github.autoservicecourseworkclient.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String name;
    private String surname;
    private String login;
    private String role;

    public LoggedInUser(String userId, String name, String surname, String login, String role) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }
}