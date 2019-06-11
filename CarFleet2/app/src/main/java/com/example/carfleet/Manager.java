package com.example.carfleet;

import java.util.List;

public class Manager {
    private int id;
    private String username;
    private List<String> users;

    public Manager(int id, String username, List<String> users) {
        this.id = id;
        this.username = username;
        this.users = users;
    }

    public Manager(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public Manager(String username, List<String> users) {
        this.username = username;
        this.users = users;
    }

    public Manager(int id) {
        this.id = id;
    }

    public Manager(String username) {
        this.username = username;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public List<String> getUsers() {return users;}

    public void setUsers(List<String> users) { this.users = users; }
}
