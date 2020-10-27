package com.company;

import static java.lang.Character.*;

public class Team {

    private String name;
    private int wins;
    private int losses;
    private double pointsFor;

    public Team(String name, int wins, int losses, double pointsFor)
    {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.pointsFor = pointsFor;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public double getPointsFor() {
        return pointsFor;
    }

    public String getRecord(){
        return Integer.toString(wins) + " - " + Integer.toString(losses);
    }

    public void addGame(char result, double score) {
        if (toUpperCase(result) == 'W') {
            wins++;
        }
        else if (toUpperCase(result) == 'L') {
            losses++;
        }
        pointsFor += score;
    }

}
