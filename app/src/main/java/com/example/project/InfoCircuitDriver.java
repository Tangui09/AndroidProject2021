package com.example.project;

public class InfoCircuitDriver
{
    private String number;
    private String position;
    private String driver;
    private String driver_firstname;
    private String driver_lastname;
    private int points;

    public InfoCircuitDriver(){}

    public InfoCircuitDriver(String number, String position, String driver, String driver_firstname, String driver_lastname, int points){
        this.driver = driver;
        this.number = number;
        this.position = position;
        this.driver_firstname = driver_firstname;
        this.driver_lastname = driver_lastname;
        this.points = points;
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

    public int getPoints() { return points; }
}
