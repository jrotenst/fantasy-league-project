package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Map<String, Double> teams = new HashMap<String, Double>();

        for (int i = 0; i < 10; i++)
        {
            String team;
            double pf;
            double score;
            System.out.println("Enter new team name:");
            team = scanner.nextLine();
            System.out.println("Enter teams Points For:");
            pf = scanner.nextDouble();
            System.out.println("Enter teams score for this week:");
            score = scanner.nextDouble();
            teams.put(team, pf + score);
            scanner.nextLine(); // flush
        }

        for (Map.Entry<String, Double> team : teams.entrySet())
        {
            System.out.println(team.getKey() + ": " + team.getValue() + "\n");
        }
    }
}
