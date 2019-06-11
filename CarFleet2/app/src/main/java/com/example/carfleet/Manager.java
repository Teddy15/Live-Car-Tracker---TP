package com.example.carfleet;

public class Manager {
    private int id;
    private String username;

    public Manager(int id, String username) {
        this.id = id;
        this.username = username;
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
}
