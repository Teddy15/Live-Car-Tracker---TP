package com.example.carfleet;

public class Group  {
    private int id;
    private String name;
    private String users;
    private String manager;

    public Group(String name, String users, String manager) {
        this.name = name;
        this.manager = manager;
        this.users = users;
    }
    public Group(int id, String name, String users, String manager) {
        this.name = name;
        this.manager = manager;
        this.users = users;
        this.id = id;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getManager() {return manager;}

    public void setManager(String manager) {this.manager = manager;}

    public String getUsers() {return users;}

    public void setUsers(String users) {this.users = users;}
}
