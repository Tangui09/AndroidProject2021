package com.example.project.utils;

public class InfoCircuit {
    private String number;
    private String position;
    private String driver;
    private String driver_firstname;
    private String driver_lastname;

    public InfoCircuit(String number,String position, String driver, String driver_firstname, String driver_lastname){
        this.driver = driver;
        this.number = number;
        this.position = position;
        this.driver_firstname = driver_firstname;
        this.driver_lastname = driver_lastname;
    }

    public String getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public String getDriver() {
        return driver;
    }

    public String getDriver_firstname() { return driver_firstname; }

    public String getDriver_lastname() { return driver_lastname; }
}
