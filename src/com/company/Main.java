package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Team> teams = new ArrayList<Team>();

        for (int i = 0; i < 10; i++)
        {
            System.out.println("Enter new team name:");
            String name = scanner.nextLine();
            System.out.println("Enter Wins:");
            int wins = scanner.nextInt();
            System.out.println("Enter Losses:");
            int losses = scanner.nextInt();
            System.out.println("Enter Points For:");
            double pf = scanner.nextDouble();
            System.out.println("Enter matchup score:");
            double score = scanner.nextDouble();
            teams.add(new Team(name, wins, losses, pf+score));
            scanner.nextLine(); // flush
        }

        System.out.println("\n\n***************LEAGUE STANDINGS***************\n\n");

        Collections.sort(teams, Team.recordComparator); // sort by record

        int i = 1;
        for (Team t : teams) {
            System.out.println((i++) + ") " + t.getName() + ": " + t.getRecord() + " (" + t.getPointsFor() + ")");
        }

        System.out.println("\n\n***************LEAGUE STANDINGS SORTED BY POINTS FOR***************\n\n");

        Collections.sort(teams, Team.pointsComparator);

        for (Team t : teams) {
            System.out.println((i++) + ") " + t.getName() + ": " + t.getRecord() + " (" + t.getPointsFor() + ")");
        }
    }
}
