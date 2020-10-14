package model;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestTeam {
    User user1 = new User("User 1");
    User user2 = new User("User 2");
    User user3 = new User("User 3");
    String defaultTestTeam = "Default Test Team";
    Team testTeam = new Team(defaultTestTeam);


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
        ArrayList<User> actualUserList = testTeam.getUsers();
        User actualUser = actualUserList.get(0);
        assertEquals(user1, actualUser);
    }

}
