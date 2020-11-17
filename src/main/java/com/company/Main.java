package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            String file = "C:\\Users\\yrote\\IdeaProjects\\fantasy-league-project" +
                    "\\src\\main\\java\\com\\company\\teams.json";
            FileReader reader = new FileReader(file);

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray teamsList = (JSONArray) obj;
            System.out.println(teamsList);

            //Iterate over teamList array and parse to objects
            teamsList.forEach(team -> teams.add(parseTeamObject((JSONObject) team)));

            // display standard league standings
            displayStandings(teams, Team.recordComparator, "LEAGUE STANDINGS");

            // display teams in order of pointsFor
            displayStandings(teams, Team.pointsComparator, "TEAMS SORTED BY POINTS FOR");

            updateJSONFile(file, teams);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateJSONFile(String file, ArrayList<Team> teams) {
        JSONArray teamsList = parseToJsonArray(teams);
        String jString = teamsList.toJSONString();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray parseToJsonArray(ArrayList<Team> teams) {
        JSONArray teamsList = new JSONArray();
        for (Team t : teams) {
            JSONObject jo = new JSONObject();
            jo.put("name", t.getName());
            jo.put("wins", t.getWins());
            jo.put("losses", t.getLosses());
            jo.put("pointsFor", round(t.getPointsFor(), 2));
            jo.put("score", "");
            teamsList.add(jo);
        }
        return teamsList;
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

        System.out.println("\n\n" + "*".repeat(15) + header + "*".repeat(15) + "\n");

        // sort teams
        Collections.sort(teams, comparator);

        // display teams
        int i = 1;
        for (Team t : teams)
        {
           System.out.print((i++) + ") " + t.getName());
           System.out.print(": " + t.getRecord() + " (");
           System.out.println(round(t.getPointsFor(), 2) + ")");
        }
    }

    public static double round(double value, int places) {

        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
