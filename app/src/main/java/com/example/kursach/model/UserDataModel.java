package com.example.kursach.model;

public class UserDataModel {

    private Long id;
    private String login;
    private String password;
    private String username;
    private Long userVacancies;
    private Long assignedVacancies;

    public UserDataModel(Long id, String login, String password, String username, Long userVacancies, Long assignedVacancies) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.username = username;
        this.userVacancies = userVacancies;
        this.assignedVacancies = assignedVacancies;
    }

    public UserDataModel(String login, String password, String username) {
        this.login = login;
        this.password = password;
        this.username = username;
    }

    public UserDataModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserVacancies() {
        return userVacancies;
    }

    public void setUserVacancies(Long userVacancies) {
        this.userVacancies = userVacancies;
    }

    public Long getAssignedVacancies() {
        return assignedVacancies;
    }

    public void setAssignedVacancies(Long assignedVacancies) {
        this.assignedVacancies = assignedVacancies;
    }
}
