package com.solution.best.app.dynamic.model;

import java.io.Serializable;

/**
 * Java BEAN
 * <p>
 * @author Grupa1
 */
public class User implements Serializable {

    private String username;
    private String password;

    public User() {
        super();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login() {
        return "admin".equals(username) && "admin".equals(password);
    }

    public String message() {
        return login() ? "Logovan korisnik" : "Nije logovan korisnik";
    }
}
