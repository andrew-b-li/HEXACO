package model;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<User> users;

    public Team(String teamName) {
        this.teamName = teamName;
        this.users = new ArrayList<User>();
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
