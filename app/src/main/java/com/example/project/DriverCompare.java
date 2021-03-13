package com.example.project;

public class DriverCompare implements Comparable<DriverCompare>{

    private String Drivername;
    private Integer Numberwin;
    private String bestperformance;
    private Integer numberrace;


    public DriverCompare(String drivername, Integer numberwin,String bestperformance,Integer numberrace){
        this.Drivername = drivername;
        this.Numberwin = numberwin;
        this.bestperformance = bestperformance;
        this.numberrace = numberrace;
    }
    public DriverCompare(String drivername){
        this.Drivername = drivername;
        this.bestperformance = "compare list";
        this.Numberwin = 0;
    }

    public String getDrivername() {
        return Drivername;
    }

    public Integer getNumberwin() {
        return Numberwin;
    }

    public String getBestperformance() {
        return bestperformance;
    }

    public Integer getNumberrace() {
        return numberrace;
    }

    @Override
    public int compareTo(DriverCompare o) {
        return this.getNumberwin() - o.getNumberwin();
    }
}
