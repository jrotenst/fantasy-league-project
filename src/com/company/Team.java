package com.company;

import java.util.Comparator;

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

    // comparator for sorting teams by record
    public static Comparator<Team> recordComparator = new Comparator<Team>() {
        @Override
        public int compare(Team t1, Team t2) {
            if (t1.wins == t2.wins) {      // in case of tiebreaker compare by points for
                return pointsComparator.compare(t1, t2);
            }
            return t2.wins - t1.wins;
        }
    };

    // comparator for sorting teams by points for
    public static Comparator<Team> pointsComparator = new Comparator<Team>() {
        @Override
        public int compare(Team t1, Team t2) {
            if (t1.pointsFor > t2.pointsFor) {
                return -1;
            }
            if (t1.pointsFor < t2.pointsFor) {
                return 1;
            }
            return 0;
        }
    };

}
