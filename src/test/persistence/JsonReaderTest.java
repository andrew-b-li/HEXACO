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

// Testing methods for JsonReader
// This class is based on the CPSC 210 Json Serialization Demo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderTest {
    Team testTeam1 = new Team("Test Team 1");
    User testUser1 = new User("Test User 1");
    Assessment testAssessment1 = new Assessment(1,1,1,1,1,1);

    Team testTeam2 = new Team("Test Team 2");
    User testUser2 = new User("Test User 2");
    Assessment testAssessment2 = new Assessment(2,2,2,2,2,2);
    Assessment testAssessment3 = new Assessment(3,3,3,3,3,3);


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            App testApp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/EmptyApp.json");
        try {
            App actualApp = reader.read();
            App expectedApp = new App();
            assertEquals(expectedApp.toString(), actualApp.toString());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderApp.json");
        try {
            App actualApp = reader.read();
            App expectedApp = new App();
            expectedApp.addTeam(testTeam1);
            expectedApp.addTeam(testTeam2);
            testTeam1.addUser(testUser1);
            testUser1.addAssessment(testAssessment1);
            testTeam2.addUser(testUser2);
            testUser2.addAssessment(testAssessment2);
            testUser2.addAssessment(testAssessment3);
            assertEquals(expectedApp.toString(), actualApp.toString());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}