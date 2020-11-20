package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 * Represents an App with a list of Teams.
 */
public class App {
    private ArrayList<Team> teamList;

    public App() {
        teamList = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teamList.add(team);
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    //Effects: If a team name is already used by a Team in the list of Teams, return that Team. Otherwise, return a
    //          new Team with the given team name.
    public Team teamExists(String teamName) {
        boolean teamExists = false;
        Team currentTeam = new Team(teamName);
        for (Team team: teamList) {
            if (teamName.equals(team.getTeamName())) {
                currentTeam = team;
                teamExists = true;
            }
        }
        if (!teamExists) {
            teamList.add(currentTeam);
        }
        return currentTeam;
    }

    @Override
    //Effects: Returns a String with a list of teams their contained users
    public String toString() {
        String list = new String();
        if (!teamList.isEmpty()) {
            if (teamList.size() == 1) {
                Team team = teamList.get(0);
                list = team.toString();
            } else {
                for (Team team : teamList) {
                    list = list + team.toString() + "\n";
                }
                list = list.substring(0, list.length() - 1);
            }
        }
        return list;
    }

    //Effects: Calls teamsToJson to return a list of Teams in App as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Teams", teamsToJson());
        return json;
    }

    //Effects: Iterates through the Teams in App and returns the information as a JSONArray
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Team team : teamList) {
            jsonArray.put(team.toJson());
        }
        return jsonArray;
    }

    //Effects: Clears teamList
    public void clearTeams() {
        teamList.clear();
    }
}
