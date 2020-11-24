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
    //Effects: If a userList does not already include a User, adds the User to the list of Users
    //calls addToTeam to add the Team to the User
    public void addUser(User user) {
        if (!this.userList.contains(user)) {
            this.userList.add(user);
            user.addToTeam(this);
        }
    }

    //Modifies: this
    //Effects: If userList contains a User, removes the User from the list of Users
    //calls removeTeam to remove the Team from the User
    public void removeUser(User user) {
        if (this.userList.contains(user)) {
            this.userList.remove(user);
            user.removeTeam(this);
        }
    }

    //Effects: If a username is already used by a User in the list of Users, return that user.
    //Else, if a username is already used by a User in a different Team, move the User into the current list of users.
    //If none of the above are true, return a new user with the given username and add it to the Team
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

    //Effects: Determines whether two Teams objects are equal (based on teamName)
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

    //Effects: Generates hash code for a Team (based on teamName)
    @Override
    public int hashCode() {
        return teamName != null ? teamName.hashCode() : 0;
    }
}
