package model;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<User> userList;

    public Team(String teamName) {
        this.teamName = teamName;
        this.userList = new ArrayList<User>();
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void addUser(User user) {
        this.userList.add(user);
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    @Override
    public String toString() {
        String list = this.getTeamName() + ": ";
        if (!userList.isEmpty()) {
            if (userList.size() == 1) {
                User user = userList.get(0);
                list = list + user.getUserName();
            } else {
                for (User user : userList) {
                    list = list + user.getUserName() + ", ";
                }
                list = list.substring(0, list.length() - 2);
            }
        } else {
            list = list.substring(0, list.length() - 1);
        }
        return list;
    }
}
