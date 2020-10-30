package persistence;

import model.App;
import model.Assessment;
import model.Team;
import model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.PersonalityApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.
    Team testTeam1 = new Team("Test Team 1");
    User testUser1 = new User("Test User 1");
    Assessment testAssessment1 = new Assessment(1,1,1,1,1,1);

    Team testTeam2 = new Team("Test Team 2");
    User testUser2 = new User("Test User 2");
    Assessment testAssessment2 = new Assessment(2,2,2,2,2,2);
    Assessment testAssessment3 = new Assessment(3,3,3,3,3,3);

    @BeforeEach
    void setup(){
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
    void testWriterGeneralPersonalityApp() {
        try {
            App testApp = new App();
            testApp.addTeam(testTeam1);
            testApp.addTeam(testTeam2);
            JsonWriter writer = new JsonWriter("./data/testWriterApp.json");
            writer.open();
            writer.write(testApp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterApp.json");
            App yourApp = reader.read();
            ArrayList<Team> readTeams = yourApp.getTeamList();
            assertEquals(2, readTeams.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}