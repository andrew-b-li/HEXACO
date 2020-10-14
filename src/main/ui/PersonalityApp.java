package ui;

import model.Assessment;
import model.Team;
import model.User;

import java.util.*;

public class PersonalityApp {
    private ArrayList<Team> teamList = new ArrayList<>();
    Scanner keyboard = new Scanner(System.in);

    public PersonalityApp() {
        runPersonality();
    }

    public void runPersonality() {
        System.out.println("Welcome to the Digital HEXACO personality test.");
        System.out.println("Current team list:");
        System.out.println(this.toString());
        System.out.println("Please enter your team name:");
        String teamName = keyboard.nextLine();
        Team newTeam = new Team(teamName);
        teamList.add(newTeam);
        System.out.println("Please enter a team member's name:");
        String newUserName = keyboard.nextLine();
        while (!newUserName.equals("")) {
            User newUser = new User(newUserName);
            Assessment newAssessment = new Assessment(0, 0, 0, 0, 0, 0);
            runAssessment(newUser, newAssessment);
            keyboard.nextLine();
            System.out.println("Please enter a team member's name:");
            newUserName = keyboard.nextLine();
        }
        System.out.println("Current team List:");
        System.out.println(this.toString());
    }

    public void runAssessment(User user, Assessment assessment) {
        System.out.println("Please answer the following questions the the best of your ability:");
        System.out.println("1 = strongly disagree, 2 = disagree, 3 = neutral, 4 = agree, 5 = strongly agree");
        System.out.println(Assessment.QUESTION2C);
        int question2CAnswer = keyboard.nextInt();
        assessment.setConscientiousness(question2CAnswer);
        System.out.println(Assessment.QUESTION3A);
        int question3AAnswer = keyboard.nextInt();
        assessment.setAgreeableness(question3AAnswer);
        System.out.println(Assessment.QUESTION4X);
        int question4XAnswer = keyboard.nextInt();
        assessment.setExtraversion(question4XAnswer);
        System.out.println(Assessment.QUESTION5E);
        int question5EAnswer = keyboard.nextInt();
        assessment.setEmotionality(question5EAnswer);
        System.out.println(Assessment.QUESTION6H);
        int question6HAnswer = keyboard.nextInt();
        assessment.setHonestyHumility(question6HAnswer);
        System.out.println(Assessment.QUESTION7O);
        int question7OAnswer = keyboard.nextInt();
        assessment.setOpennessToExperience(question7OAnswer);
        user.addAssessment(assessment);
    }

    @Override
    public String toString() {
        String list = new String();
        if (!teamList.isEmpty()) {
            if (teamList.size() == 1) {
                Team team = teamList.get(0);
                list = team.toString();
            } else {
                for (Team team : teamList) {
                    list = list + team.toString() + ",";
                }
                list = list.substring(0, list.length() - 1);
            }
        }
        return list;
    }
}
