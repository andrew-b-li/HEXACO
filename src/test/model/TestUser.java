package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        ArrayList<Assessment> actualAssessmentList = testUser.getAssessments();
        Assessment actualAssessment = actualAssessmentList.get(0);
        assertEquals(testAssessment1, actualAssessment);
    }

}
