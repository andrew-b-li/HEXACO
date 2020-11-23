package model;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the Team class.
 */
public class TestTeam {
    User user1 = new User("User 1");
    User user2 = new User("User 2");
    String defaultTestTeam = "Default Test Team";
    Team testTeam = new Team(defaultTestTeam);
    App testApp = new App();

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
    void testRemoveUser(){
        testTeam.addUser(user1);
        testTeam.addUser(user2);
        testTeam.removeUser(user2);
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

    @Test
    void testUserExistsDoesExist(){
        testTeam.addUser(user1);
        String expectedUserName = "User 1";
        assertEquals(user1, testTeam.userExists(expectedUserName, testApp));
    }

    @Test
    void testUserExistsDoesNotExist(){
        testTeam.addUser(user1);
        User newUser = testTeam.userExists("User 2", testApp);
        assertEquals( "User 2", newUser.getUserName());
    }

}
