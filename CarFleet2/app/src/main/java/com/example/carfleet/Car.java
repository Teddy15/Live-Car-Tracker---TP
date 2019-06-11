package com.example.carfleet;

import java.util.Date;

public class Car {

    private int id;

    private String name;

    private Date releaseDate;

    public Car(int id, String name, Date releaseDate) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public Car(String name, Date releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
