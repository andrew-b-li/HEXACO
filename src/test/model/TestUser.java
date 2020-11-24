package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the User class.
 */
public class TestUser {
    User testUser = new User("Default Test User");
    User testUser1 = new User("Default Test User");
    User testUser2 = new User("Test User 2");
    Team testTeam1 = new Team("Test Team 1");
    Team testTeam2 = new Team("Test Team 2");
    String defaultTestUser = "Default Test User";

    @Test
    void testSetUserName() {
        testUser.setUserName("New Test User");
        String actualTeamName = testUser.getUserName();
        assertEquals("New Test User", actualTeamName);
    }

    @Test
    void testGetUserName(){
        String actualTestUserName = testUser.getUserName();
        assertEquals("Default Test User", actualTestUserName);
    }

    @Test
    void testAddAssessment(){
        Assessment testAssessment1 = new Assessment(2,2,2,2,2,2);
        testUser.addAssessment(testAssessment1);
        ArrayList<Assessment> actualAssessmentList = testUser.getAssessmentList();
        Assessment actualAssessment = actualAssessmentList.get(0);
        assertEquals(testAssessment1, actualAssessment);
    }

    @Test
    void testToStringNoAssessments() {
        String actualString = testUser.toString();
        String expectedString = "Default Test User:";
        assertEquals(expectedString, actualString);
    }

    @Test
    void testToStringOneUser() {
        Assessment testAssessment1 = new Assessment(2,2,2,2,2,2);
        testUser.addAssessment(testAssessment1);

        String actualString = testUser.toString();
        String expectedString = "Default Test User: H:2 E:2 X:2 A:2 C:2 O:2";
        assertEquals(expectedString, actualString);
    }

    @Test
    void testToStringTwoUsers() {
        Assessment testAssessment1 = new Assessment(1,1,1,1,1,1);
        Assessment testAssessment2 = new Assessment(2,2,2,2,2,2);

        testUser.addAssessment(testAssessment1);
        testUser.addAssessment(testAssessment2);
        String actualString = testUser.toString();
        String expectedString = "Default Test User: H:1 E:1 X:1 A:1 C:1 O:1\nH:2 E:2 X:2 A:2 C:2 O:2";
        assertEquals(expectedString, actualString);
    }

    @Test
    public void testEquals() {
        assertEquals(testUser, testUser1);
        assertEquals(testUser, testUser);
        assertFalse(testUser.equals(testUser2));
        assertFalse(testUser.equals(null));
        assertFalse(testUser.equals(testTeam1));
        testUser1.setUserName(null);
        assertFalse(testUser1.equals(testUser));
        testUser.setUserName(null);
        assertTrue(testUser1.equals(testUser));
    }

    @Test
    public void testHashCode() {
        assertEquals(testUser.hashCode(), testUser1.hashCode());
        assertEquals(testUser.hashCode(), testUser.hashCode());
        assertFalse(testUser.equals(testUser2));
        assertFalse(testUser.equals(null));
        testUser1.setUserName(null);
        assertEquals(0, testUser1.hashCode());
    }

    @Test
    void testAddTeamAlreadyAdded(){
        testTeam1.addUser(testUser);
        testTeam1.addUser(testUser);
        assertTrue(testTeam1.getUserList().contains(testUser));
        testUser.addToTeam(testTeam1);
        assertTrue(testTeam1.getUserList().contains(testUser));
    }

    @Test
    void testAddTeamNotAlreadyAdded(){
        testTeam1.addUser(testUser);
        testUser.addToTeam(testTeam2);
        assertTrue(testTeam2.getUserList().contains(testUser));
    }

    @Test
    public void testGetTeam() {
        testTeam1.addUser(testUser);
        assertEquals(testTeam1, testUser.getTeam());
    }

    @Test
    void testRemoveTeam(){
        testTeam1.addUser(testUser);
        assertTrue(testTeam1.getUserList().contains(testUser));
        testUser.removeTeam(testTeam2);
        assertTrue(testTeam1.getUserList().contains(testUser));
        testUser.removeTeam(testTeam1);
        assertFalse(testTeam1.getUserList().contains(testUser));
    }
}
