package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        try {
            File file = new File("C:\\Users\\yrote\\IdeaProjects\\MNFLeagueStandings\\src\\com\\company\\TeamData.txt");
            Scanner scanner = new Scanner(file);

            ArrayList<Team> teams = new ArrayList<Team>();

            for (int i = 0; i < 10; i++)
            {
                //System.out.println("Enter new team name:");
                String name = scanner.nextLine();
                //System.out.println("Enter Wins:");
                int wins = Integer.parseInt(scanner.nextLine());
                //System.out.println("Enter Losses:");
                int losses = Integer.parseInt(scanner.nextLine());
                //System.out.println("Enter Points For:");
                double pf = Double.parseDouble(scanner.nextLine());
                //System.out.println("Enter matchup score:");
                double score = Double.parseDouble(scanner.nextLine());
                teams.add(new Team(name, wins, losses, pf+score));
                //scanner.nextLine(); // flush
            }

            System.out.println("\n\n***************LEAGUE STANDINGS***************\n\n");

            Collections.sort(teams, Team.recordComparator); // sort by record

            int i = 1;
            for (Team t : teams) {
                System.out.println((i++) + ") " + t.getName() + ": " + t.getRecord() +
                        " (" + round(t.getPointsFor(), 2) + ")");
            }

            System.out.println("\n\n***************LEAGUE STANDINGS SORTED BY POINTS FOR***************\n\n");

            Collections.sort(teams, Team.pointsComparator);

            i = 0;
            for (Team t : teams) {
                System.out.println((i++) + ") " + t.getName() + ": " + t.getRecord() +
                        " (" + round(t.getPointsFor(), 2) + ")");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
