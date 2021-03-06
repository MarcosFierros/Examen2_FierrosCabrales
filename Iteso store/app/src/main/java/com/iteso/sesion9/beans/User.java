package com.iteso.sesion9.beans;

public class User {

    private String name;
    private String password;
    private boolean isLogged;

    public User() {
        setName("");
        setPassword("");
        setLooged(false);
    }

    public User(String name, String password, boolean isLogged) {
        this.name = name;
        this.password = password;
        this.isLogged = isLogged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLooged(boolean looged) {
        isLogged = looged;
    }
}
