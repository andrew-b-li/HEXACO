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

// Testing methods for JsonWriter
// This class is based on the CPSC 210 Json Serialization Demo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest {
    Team testTeam1 = new Team("Test Team 1");
    User testUser1 = new User("Test User 1");
    Assessment testAssessment1 = new Assessment(1,1,1,1,1,1);

    Team testTeam2 = new Team("Test Team 2");
    User testUser2 = new User("Test User 2");
    Assessment testAssessment2 = new Assessment(2,2,2,2,2,2);
    Assessment testAssessment3 = new Assessment(3,3,3,3,3,3);

    Team testTeam3 = new Team("Test Team 3");
    User testUser3 = new User("Test User 3");
    Assessment testAssessment4 = new Assessment(4,4,4,4,4,4);



    @BeforeEach
    void setup(){
        testTeam1.clearUsers();
        testTeam2.clearUsers();
        testTeam3.clearUsers();

        testUser1.clearAssessments();
        testUser2.clearAssessments();
        testUser3.clearAssessments();

        testTeam1.addUser(testUser1);
        testUser1.addAssessment(testAssessment1);

        testTeam2.addUser(testUser2);
        testTeam2.addUser(testUser3);
        testUser2.addAssessment(testAssessment2);
        testUser2.addAssessment(testAssessment3);

        testTeam3.addUser(testUser3);
        testUser3.addAssessment(testAssessment4);

    }

    @Test
    void testWriterInvalidFile() {
        try {
            App testApp = new App();
            testApp.addTeam(testTeam1);
            testApp.addTeam(testTeam2);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAppTest() {
        try {
            App testApp = new App();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyApp.json");
            writer.open();
            writer.write(testApp);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyApp.json");
            App yourApp = reader.read();
            assertEquals(testApp.toString(), yourApp.toString());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterPersonalityAppOneOfEach() {
        try {
            App testApp = new App();
            testApp.addTeam(testTeam3);
            JsonWriter writer = new JsonWriter("./data/testWriterAppOneOfEach.json");
            writer.open();
            writer.write(testApp);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterAppOneOfEach.json");
            App yourApp = reader.read();
            assertEquals(testApp.toString(), yourApp.toString());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPersonalityApp() {
        try {
            App testApp = new App();
            testApp.addTeam(testTeam1);
            testApp.addTeam(testTeam2);
            testApp.addTeam(testTeam3);
            JsonWriter writer = new JsonWriter("./data/testWriterApp.json");
            writer.open();
            writer.write(testApp);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterApp.json");
            App yourApp = reader.read();
            assertEquals(testApp.toString(), yourApp.toString());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}