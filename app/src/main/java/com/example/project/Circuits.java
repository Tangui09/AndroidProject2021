package com.example.project;

public class Circuits {

    private String id;
    private String Name;
    private String Locality;
    private String Country;

    public Circuits(String id,String name, String locality, String country){
        this.Name = name;
        this.Country = country;
        this.Locality = locality;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public String getLocality() {
        return Locality;
    }

    public String getCountry() {
        return Country;
    }

    public String getId() {
        return id;
    }
}
