package com.example.project;

public class DriverCompare implements Comparable<DriverCompare>{

    private String Drivername;
    private Integer Numberwin;
    private String bestperformance;


    public DriverCompare(String drivername, Integer numberwin,String bestperformance){
        this.Drivername = drivername;
        this.Numberwin = numberwin;
        this.bestperformance = bestperformance;
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

    @Override
    public int compareTo(DriverCompare o) {
        return this.getNumberwin() - o.getNumberwin();
    }
}
