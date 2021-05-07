package com.solution.best.app.dynamic.model;

import java.io.Serializable;
import java.util.Objects;

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
    
    @Override
    public int hashCode() {
        return Objects.hash(username, password); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User comparedUser = (User) obj;
        
        return username.equals(comparedUser.getUsername()) && password.equals(comparedUser.getPassword()); //To change body of generated methods, choose Tools | Templates.
    }
    
}
