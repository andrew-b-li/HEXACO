package model;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestTeam {
    User user1 = new User("User 1");
    User user2 = new User("User 2");
    String defaultTestTeam = "Default Test Team";
    Team testTeam = new Team(defaultTestTeam);


    @Test
    void testSetTeamName(){
        testTeam.setTeamName("Changed Test Team");
        String actualTeamName = testTeam.getTeamName();
        assertEquals("Changed Test Team", actualTeamName);
    }

    @Test
    void testGetTeamName(){
        String actualTeamName = testTeam.getTeamName();
        assertEquals("Default Test Team", actualTeamName);
    }

    @Test
    void testAddUser(){
        testTeam.addUser(user1);
        ArrayList<User> actualUserList = testTeam.getUserList();
        User actualUser = actualUserList.get(0);
        assertEquals(user1, actualUser);
    }

    @Test
    void testToStringNoUsers() {
        String actualString = testTeam.toString();
        String expectedString = "Default Test Team:";
        assertEquals(expectedString, actualString);
    }

    @Test
    void testToStringOneUser() {
        testTeam.addUser(user1);
        String actualString = testTeam.toString();
        String expectedString = "Default Test Team: User 1";
        assertEquals(expectedString, actualString);
    }

    @Test
    void testToStringTwoUsers() {
        testTeam.addUser(user1);
        testTeam.addUser(user2);
        String actualString = testTeam.toString();
        String expectedString = "Default Test Team: User 1, User 2";
        assertEquals(expectedString, actualString);
    }
}
