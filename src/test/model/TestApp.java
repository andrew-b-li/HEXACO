package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/*
 * Unit tests for the Assessment class.
 */
public class TestApp {
    Team testTeam1 = new Team("Test Team 1");
    User testUser1 = new User("Test User 1");
    Assessment testAssessment1 = new Assessment(1, 1, 1, 1, 1, 1);

    Team testTeam2 = new Team("Test Team 2");
    User testUser2 = new User("Test User 2");
    Assessment testAssessment2 = new Assessment(2, 2, 2, 2, 2, 2);
    Assessment testAssessment3 = new Assessment(3, 3, 3, 3, 3, 3);

    @BeforeEach
    void setup() {
        testTeam1.clearUsers();
        testTeam2.clearUsers();
        testUser1.clearAssessments();
        testUser2.clearAssessments();
        testTeam1.addUser(testUser1);
        testUser1.addAssessment(testAssessment1);
        testTeam2.addUser(testUser2);
        testUser2.addAssessment(testAssessment2);
        testUser2.addAssessment(testAssessment3);
    }

    @Test
    void testClear() {
        App testApp = new App();
        testApp.addTeam(testTeam1);
        testApp.addTeam(testTeam2);
        testApp.clearTeams();
        ArrayList<Team> clearedTeamList = testApp.getTeamList();
        assertEquals(0, clearedTeamList.size());
    }

    @Test
    void testTeamExistsDoesExist() {
        App testApp = new App();
        testApp.addTeam(testTeam1);
        testApp.addTeam(testTeam2);

        Team actualTeam = testApp.teamExists("Test Team 1");
        assertEquals(testTeam1, actualTeam);
    }

    @Test
    void testTeamExistsDoesNotExist() {
        App testApp = new App();
        testApp.addTeam(testTeam1);
        testApp.addTeam(testTeam2);

        Team actualTeam = testApp.teamExists("Test Team 3");
        assertNotEquals(testTeam1, actualTeam);
        assertNotEquals(testTeam2, actualTeam);
    }

    @Test
    void testToStringTwoTeams() {
        App testApp = new App();
        testApp.addTeam(testTeam1);
        testApp.addTeam(testTeam2);
        assertEquals("Test Team 1: Test User 1\n" +
                "Test Team 2: Test User 2", testApp.toString());
    }

    @Test
    void testToStringOneTeams() {
        App testApp = new App();
        testApp.addTeam(testTeam1);
        assertEquals("Test Team 1: Test User 1", testApp.toString());
    }

    @Test
    void testToStringNoTeams() {
        App testApp = new App();
        assertEquals("", testApp.toString());
    }
}