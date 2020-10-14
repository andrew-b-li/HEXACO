package model;

import java.util.ArrayList;

/*
 * Represents a team with a name and list of users.
 */
public class Team {
    private String teamName;
    private ArrayList<User> userList;

    //Construct a Team
    //Effects: Assigns Team a name and list to hold Users
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

    public ArrayList<User> getUserList() {
        return userList;
    }

    //Modifies: this
    //Effects: adds a User to the list of Users
    public void addUser(User user) {
        this.userList.add(user);
    }

    //Effects: If a username is already used by a User in the list of Users, return that user. Otherwise, return a
    //          new user with the given username.
    public User userExists(String userName) {
        boolean userExists = false;
        User currentUser = new User(userName);
        for (User user: this.getUserList()) {
            if (userName.equals(user.getUserName())) {
                currentUser = user;
                userExists = true;
            }
        }
        if (!userExists) {
            this.addUser(currentUser);
        }
        return currentUser;
    }

    //Effects: Returns a team's name and its users
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
