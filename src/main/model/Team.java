package model;

import org.json.JSONArray;
import org.json.JSONObject;

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
        if (!this.userList.contains(user)) {
            this.userList.add(user);
            user.addTeam(this);
        }
    }

    //Modifies: this
    //Effects: adds a User to the list of Users
    public void removeUser(User user) {
        this.userList.remove(user);
    }

//    //Effects: If a username is already used by a User in the list of Users, return that user. Otherwise, return a
//    //          new user with the given username.
//    public User userExists(String userName) {
//        boolean userExists = false;
//        User currentUser = new User(userName);
//        for (User user: this.getUserList()) {
//            if (userName.equals(user.getUserName())) {
//                currentUser = user;
//                userExists = true;
//            }
//        }
//        if (!userExists) {
//            this.addUser(currentUser);
//        }
//        return currentUser;
//    }

    //Effects: If a username is already used by a User in the list of Users, return that user. Otherwise, return a
    //          new user with the given username.
    public User userExists(String userName, App yourApp) {
        boolean userExistsInTeam = false;
        User currentUser = new User(userName);
        for (User user: this.getUserList()) {
            if (currentUser.equals(user)) {
                currentUser = user;
                userExistsInTeam = true;
            }
        }
        if (!userExistsInTeam) {
            boolean userExistsInGeneral = false;
            for (User user: yourApp.getGeneralUserSet()) {
                if (currentUser.equals((user))) {
                    currentUser = user;
                    userExistsInGeneral = true;
                }
            }
            if (!userExistsInGeneral) {
                yourApp.addGeneralUser(currentUser);
            }
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

    //Effects: Calls usersToJson to return a list of Users in a Team as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Team Name", teamName);
        json.put("Users", usersToJson());
        return json;
    }

    //Effects: Iterates through the Users in userList and returns the information as a JSONArray
    private JSONArray usersToJson() {
        JSONArray jsonArray = new JSONArray();
        for (User user : userList) {
            jsonArray.put(user.toJson());
        }
        return jsonArray;
    }

    //Effects: Clears userList
    public void clearUsers() {
        userList.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Team team = (Team) o;

        return teamName != null ? teamName.equals(team.teamName) : team.teamName == null;
    }

    @Override
    public int hashCode() {
        return teamName != null ? teamName.hashCode() : 0;
    }
}
