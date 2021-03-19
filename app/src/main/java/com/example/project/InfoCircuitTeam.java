package com.example.project;

public class InfoCircuitTeam
{
    private String position;
    private String team_name;
    private int points = 0;

    public InfoCircuitTeam(String position, String team_name, int points){
        this.position = position;
        this.team_name = team_name;
        this.points = points;
    }

    public String getPosition() {
        return position;
    }

    public String getTeamName() { return team_name; }

    public int getPoints() { return points; }
}
