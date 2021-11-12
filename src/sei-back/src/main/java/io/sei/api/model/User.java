package io.sei.api.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class User {
    private String[] username;
    public String[] getUsername() {
        return username;
    }
    public void setUsername(String[] username) {
        this.username = username;
    }
    public String[] getPassword() {
        return password;
    }
    public void setPassword(String[] password) {
        this.password = password;
    }
    private String[] password;
    @Id @GeneratedValue
    private int id;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;

    }
}
