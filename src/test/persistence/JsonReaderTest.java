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
    Assessment testAssessment1 = new Assessment(1, 1, 1, 1, 1, 1);
    Team testTeam2 = new Team("Test Team 2");
    User testUser2 = new User("Test User 2");
    Assessment testAssessment2 = new Assessment(2, 2, 2, 2, 2, 2);
    Assessment testAssessment3 = new Assessment(3, 3, 3, 3, 3, 3);
    Team testTeam3 = new Team("Test Team 3");
    User testUser3 = new User("Test User 3");
    Assessment testAssessment4 = new Assessment(4, 4, 4, 4, 4, 4);


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
            expectedApp.addTeam(testTeam3);
            this.testTeam1.addUser(this.testUser1);
            this.testUser1.addAssessment(this.testAssessment1);
            this.testTeam2.addUser(this.testUser2);
            this.testTeam2.addUser(this.testUser3);
            this.testUser2.addAssessment(this.testAssessment2);
            this.testUser2.addAssessment(this.testAssessment3);
            this.testTeam3.addUser(this.testUser3);
            this.testUser3.addAssessment(this.testAssessment4);
            assertEquals(expectedApp.toString(), actualApp.toString());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}