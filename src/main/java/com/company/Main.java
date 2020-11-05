package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        ArrayList<Team> teams = new ArrayList<Team>();

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try
        {
            String file = "C:\\Users\\yrote\\IdeaProjects\\fantasy-league-project\\src\\main\\java\\com\\company\\teams.json";
            FileReader reader = new FileReader(file);

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray teamsList = (JSONArray) obj;
            System.out.println(teamsList);

            //Iterate over teamList array and parse to objects
            teamsList.forEach(team -> teams.add(parseTeamObject((JSONObject) team)));

            // display standard league standings
            String header = "*".repeat(15) + "LEAGUE STANDINGS" + "*".repeat(15);
            displayStandings(teams, Team.recordComparator, header);

            header = "*".repeat(15) + "TEAMS SORTED BY POINTS FOR" + "*".repeat(15);
            displayStandings(teams, Team.pointsComparator, header);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Team parseTeamObject(JSONObject team) {

        //Get team object within list
        JSONObject teamObject = (JSONObject) team.get("team");

        String name = (String) teamObject.get("name");
        int wins = Integer.parseInt(((String) teamObject.get("wins")));
        int losses = Integer.parseInt(((String) teamObject.get("losses")));
        double pf = Double.parseDouble((String) teamObject.get("pointsFor"));
        double score = Double.parseDouble((String) teamObject.get("score"));
        return new Team(name, wins, losses, pf+score);
    }

    public static void displayStandings(ArrayList<Team> teams, Comparator<Team> comparator, String header) {
        System.out.println("\n\n" + header + "\n");

        // sort teams
        Collections.sort(teams, comparator); // sort by record

        // display teams
        int i = 1;
        for (Team t : teams)
        {
            System.out.println((i++) + ") " + t.getName() + ": " + t.getRecord() +
                    " (" + round(t.getPointsFor(), 2) + ")");
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
