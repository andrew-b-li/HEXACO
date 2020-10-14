package ui;

import model.Assessment;
import model.Team;
import model.User;

import java.util.*;

public class PersonalityApp {
    private ArrayList<Team> teamList = new ArrayList<>();
    Scanner keyboard = new Scanner(System.in);

    public PersonalityApp() {
        System.out.println("Welcome to the Digital HEXACO personality test.");
        System.out.println("Team list:");
        runPersonality();
    }

    public void runPersonality() {
        for (Team team: teamList) {
            team.getTeamName();
        }
        System.out.println("Please enter your team name:");
        String teamName = keyboard.nextLine();
        Team currentTeam = teamExists(teamName);
        System.out.println("Your team:");
        System.out.println(currentTeam.toString());
        System.out.println("Please enter a team member's name:");
        String userName = keyboard.nextLine();
        while (!userName.equals("")) {
            userName = addUserAndAssessment(currentTeam, userName);
        }
        System.out.println("Team List:");
        System.out.println(this.toString());
    }

    public String addUserAndAssessment(Team currentTeam, String userName) {
        User currentUser = currentTeam.userExists(userName);
        Assessment newAssessment = new Assessment(0, 0, 0, 0, 0, 0);
        runAssessment(currentUser, newAssessment);
        System.out.println("Your team:");
        System.out.println(currentTeam.toString());
        keyboard.nextLine();
        System.out.println("Please enter a team member's name:");
        userName = keyboard.nextLine();
        return userName;
    }


    public Team teamExists(String teamName) {
        boolean teamExists = false;
        Team currentTeam = new Team(teamName);
        for (Team team: teamList) {
            if (teamName.equals(team.getTeamName())) {
                currentTeam = team;
                teamExists = true;
            }
        }
        if (!teamExists) {
            teamList.add(currentTeam);
        }
        return currentTeam;
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
        System.out.println("The assessment is complete!");
        System.out.println("");
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
