package model;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/*
 * Unit tests for the Team class.
 */
public class TestTeam {
    User user1 = new User("User 1");
    User user2 = new User("User 2");
    String defaultTestTeam = "Default Test Team";
    Team testTeam1 = new Team(defaultTestTeam);
    Team testTeam2 = new Team("Test Team 2");
    Team testTeam3 = new Team("Test Team 3");
    Team testTeam4 = new Team("Default Test Team");

    App testApp = new App();

    @Test
    void testSetTeamName(){
        testTeam1.setTeamName("Changed Test Team");
        String actualTeamName = testTeam1.getTeamName();
        assertEquals("Changed Test Team", actualTeamName);
    }

    @Test
    void testGetTeamName(){
        String actualTeamName = testTeam1.getTeamName();
        assertEquals("Default Test Team", actualTeamName);
    }

    @Test
    void testAddUser(){
        testTeam1.addUser(user1);
        ArrayList<User> actualUserList = testTeam1.getUserList();
        User actualUser = actualUserList.get(0);
        assertEquals(user1, actualUser);
    }

    @Test
    void testRemoveUser(){
        testTeam1.addUser(user1);
        testTeam1.addUser(user2);
        testTeam1.removeUser(user2);
        ArrayList<User> actualUserList = testTeam1.getUserList();
        User actualUser = actualUserList.get(0);
        assertEquals(user1, actualUser);
    }

    @Test
    void testToStringNoUsers() {
        String actualString = testTeam1.toString();
        String expectedString = "Default Test Team:";
        assertEquals(expectedString, actualString);
    }

    @Test
    void testToStringOneUser() {
        testTeam1.addUser(user1);
        String actualString = testTeam1.toString();
        String expectedString = "Default Test Team: User 1";
        assertEquals(expectedString, actualString);
    }

    @Test
    void testToStringTwoUsers() {
        testTeam1.addUser(user1);
        testTeam1.addUser(user2);
        String actualString = testTeam1.toString();
        String expectedString = "Default Test Team: User 1, User 2";
        assertEquals(expectedString, actualString);
    }

    @Test
    void testUserExistsDoesExist(){
        testApp.addTeam(testTeam1);
        testTeam1.addUser(user1);
        testApp.addGeneralUser(user1);
        String expectedUserName = "User 1";
        assertEquals(user1, testTeam1.userExists(expectedUserName, testApp));
    }

    @Test
    void testUserExistsDoesNotExist(){
        testApp.addTeam(testTeam1);
        testTeam1.addUser(user1);
        testApp.addGeneralUser(user1);
        assertEquals(user2, testTeam1.userExists("User 2", testApp));
    }

    @Test
    void testUserNotExistTeamDoesExistGeneral(){
        testApp.addTeam(testTeam1);
        testApp.addTeam(testTeam2);
        testTeam1.addUser(user1);
        testTeam2.addUser(user2);
        testApp.addGeneralUser(user1);
        testApp.addGeneralUser(user2);
        assertEquals(user2, testTeam1.userExists("User 2", testApp));
    }

    @Test
    void testSwitchTeam(){
        testApp.addTeam(testTeam1);
        testApp.addTeam(testTeam2);
        testTeam1.addUser(user1);
        testApp.addGeneralUser(user1);
        testTeam2.addUser(user1);
        assertFalse(testTeam1.getUserList().contains(user1));
        assertTrue(testTeam2.getUserList().contains(user1));
    }


    @Test
    public void testEquals() {
        assertEquals(testTeam1, testTeam1);
        assertEquals(testTeam1, testTeam4);
        assertFalse(testTeam1.equals(testTeam2));
        assertFalse(testTeam1.equals(null));
        assertFalse(testTeam1.equals(testApp));
    }

    @Test
    public void testHashCode() {
        assertEquals(testTeam1.hashCode(), testTeam4.hashCode());
        testTeam1.setTeamName(null);
        assertEquals(0, testTeam1.hashCode());
    }
}
