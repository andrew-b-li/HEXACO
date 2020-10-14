package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the User class.
 */
public class TestUser {
    User testUser = new User("Default Test User");
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
        String expectedString = "Default Test User: H:1 E:1 X:1 A:1 C:1 O:1, H:2 E:2 X:2 A:2 C:2 O:2";
        assertEquals(expectedString, actualString);
    }

}
