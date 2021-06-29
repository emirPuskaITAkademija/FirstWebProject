package com.solution.best.app.dynamic.user;

import java.io.Serializable;
import javax.inject.Inject;

/**
 * Java Bean
 * <li>1. Serializable
 * <li>2. Podrazumijevani konstruktor
 * <li>3. fields -> get set -> properties ili svojstva
 *
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
}
