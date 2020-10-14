package model;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<User> users;

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return teamName;
    }

}
