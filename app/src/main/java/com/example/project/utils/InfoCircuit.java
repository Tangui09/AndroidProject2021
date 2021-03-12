package com.example.project.utils;

public class InfoCircuit {
    private String number;
    private String position;
    private String driver;
    public InfoCircuit(String number,String position, String driver){
        this.driver = driver;
        this.number = number;
        this.position = position;
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
}
