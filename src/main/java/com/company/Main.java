package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        ArrayList<Team> teams = new ArrayList<Team>();

        try
        {
            FileReader reader = new FileReader("C:\\Users\\yrote\\IdeaProjects\\fantasy-league-project\\src\\main\\java\\com\\company\\teams.json");

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray teamsList = (JSONArray) obj;
            System.out.println(teamsList);

            //Iterate over employee array
            teamsList.forEach(team -> teams.add(parseTeamObject((JSONObject) team)));

            System.out.println("\n\n***************LEAGUE STANDINGS***************\n\n");

            Collections.sort(teams, Team.recordComparator); // sort by record

            int i = 1;
            for (Team t : teams) {
                System.out.println((i++) + ") " + t.getName() + ": " + t.getRecord() +
                        " (" + round(t.getPointsFor(), 2) + ")");
            }

            System.out.println("\n\n***************LEAGUE STANDINGS SORTED BY POINTS FOR***************\n\n");

            Collections.sort(teams, Team.pointsComparator);

            i = 1;
            for (Team t : teams) {
                System.out.println((i++) + ") " + t.getName() + ": " + t.getRecord() +
                        " (" + round(t.getPointsFor(), 2) + ")");
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
