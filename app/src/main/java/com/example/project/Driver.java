package com.example.project;

public class Driver
{
    private String name;
    private String firstname;
    private String id;

    public Driver(String firstname, String name, String id) {
        this.name = name;
        this.firstname = firstname;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getId() {
        return id;
    }
}
