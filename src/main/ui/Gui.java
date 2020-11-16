package ui;

import model.App;
import model.Assessment;
import model.Team;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * Represents the personality test app with a graphical user interface.
 * Allows users to create teams, users, and assessments.
 * Allows the user to load and save data.
 */
public class Gui extends Frame implements ActionListener {
    private static final String JSON_STORE = "./data/App.json";
    private App yourApp = new App();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Team currentTeam = new Team("");
    private User currentUser = new User("");

    JFrame frame = new JFrame();
    JPanel panelContainer = new JPanel();
    JPanel panelLoadSave = new JPanel();

    JPanel panelTeams = new JPanel();
    JPanel panelTeamsButtons = new JPanel();
    JPanel panelNewTeam = new JPanel();
    JTextField newTeamName = new JTextField(20);
    JButton enterNewTeamName = new JButton("New Team");

    JPanel panelUsers = new JPanel();
    JPanel panelUsersButtons = new JPanel();
    JPanel panelNewUser = new JPanel();
    JTextField newUserName = new JTextField(20);
    JButton enterNewUserName = new JButton("New User");

    JButton backToTeams = new JButton("Back to Teams");

    JPanel panelAssessments = new JPanel();
    JButton newAssessment = new JButton("New Assessment");
    JButton backToUsers = new JButton("Back to Users");
    JButton removeUser = new JButton("Remove User");

    JPanel panelNewAssessment = new JPanel();
    JTextArea newAssessmentTextArea = new JTextArea();
    JTextField question1 = new JTextField();
    JTextField question2 = new JTextField();
    JTextField question3 = new JTextField();
    JTextField question4 = new JTextField();
    JTextField question5 = new JTextField();
    JTextField question6 = new JTextField();
    JButton submitAssessment = new JButton("Submit Assessment");

    JPanel panelExitSave = new JPanel();

    JButton buttonNew = new JButton("No - Start Fresh");
    JButton buttonLoad = new JButton("Yes - Load Previous");
    JButton buttonEnd = new JButton("End Session");

    String textLoadSaveOne = "Welcome to the Digital HEXACO personality test.";
    String textLoadSaveTwo = "Would you like to load data from a previous session?";

    JTextArea textLoadSave = new JTextArea(textLoadSaveOne + "\n" + textLoadSaveTwo);
    JTextArea textTeams = new JTextArea();
    JTextArea textUsers = new JTextArea();
    JTextArea textAssessments = new JTextArea();
    JTextArea textExitSave = new JTextArea("Before you go, would you like to save your changes?");

    JButton buttonNoSave = new JButton("No - Discard Changes");
    JButton buttonYesSave = new JButton("Yes - Save Changes");

    CardLayout cl = new CardLayout();

    public Gui() {
        setupContainer();
        setupLoadSavePanel();
        setupExitSavePanel();
        setupTeamsUsersAssessmentsPanel();
        addActionListeners();
        setEmptyNames();

        frame.add(panelContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public void setEmptyNames() {
        buttonNew.setName("");
        buttonLoad.setName("");
        buttonEnd.setName("");
        backToUsers.setName("");
        newAssessment.setName("");
        newTeamName.setName("");
        enterNewTeamName.setName("");
        backToTeams.setName("");
        newUserName.setName("");
        enterNewUserName.setName("");
        question1.setName("");
        question2.setName("");
        question3.setName("");
        question4.setName("");
        question5.setName("");
        question6.setName("");
        submitAssessment.setName("");
        buttonNoSave.setName("");
        buttonYesSave.setName("");
        removeUser.setName("");
    }

    public void setupContainer() {
        panelContainer.setLayout(cl);
        panelContainer.setPreferredSize(new Dimension(800,800));
        panelContainer.add(panelLoadSave, "1");
        panelContainer.add(panelTeams, "2");
        panelContainer.add(panelUsers, "3");
        panelContainer.add(panelAssessments, "4");
        panelContainer.add(panelNewAssessment, "5");
        panelContainer.add(panelExitSave, "6");
    }

    public void addActionListeners() {
        buttonNew.addActionListener(this);
        buttonLoad.addActionListener(this);
        buttonEnd.addActionListener(this);
        buttonNoSave.addActionListener(this);
        buttonYesSave.addActionListener(this);
        backToUsers.addActionListener(this);
        backToTeams.addActionListener(this);
        newAssessment.addActionListener(this);
        newTeamName.addActionListener(this);
        enterNewTeamName.addActionListener(this);
        newUserName.addActionListener(this);
        enterNewUserName.addActionListener(this);
        question1.addActionListener(this);
        question2.addActionListener(this);
        question3.addActionListener(this);
        question4.addActionListener(this);
        question5.addActionListener(this);
        question6.addActionListener(this);
        submitAssessment.addActionListener(this);
        removeUser.addActionListener(this);
    }

    public void setupTeamsUsersAssessmentsPanel() {
        panelTeams.setLayout(new BoxLayout(panelTeams, BoxLayout.PAGE_AXIS));
        panelTeams.add(textTeams);
        panelTeamsButtons.setLayout(new GridLayout(4,0));
        panelTeams.add(panelTeamsButtons);
        panelTeams.add(panelNewTeam);
        panelNewTeam.add(newTeamName);
        panelNewTeam.add(enterNewTeamName);
        panelTeams.add(buttonEnd);

        panelUsers.setLayout(new BoxLayout(panelUsers, BoxLayout.PAGE_AXIS));
        panelUsers.add(textUsers);
        panelUsersButtons.setLayout(new GridLayout(4,0));
        panelUsers.add(panelUsersButtons);
        panelUsers.add(panelNewUser);
        panelNewUser.add(newUserName);
        panelNewUser.add(enterNewUserName);
        panelUsers.add(backToTeams);

        panelAssessments.setLayout(new BoxLayout(panelAssessments, BoxLayout.PAGE_AXIS));
        panelAssessments.add(textAssessments);
        panelAssessments.add(newAssessment);
        panelAssessments.add(removeUser);
        panelAssessments.add(backToUsers);
        setupNewAssessmentPanel();
    }

    public void setupNewAssessmentPanel() {
        panelNewAssessment.setLayout(new BoxLayout(panelNewAssessment, BoxLayout.PAGE_AXIS));
        panelNewAssessment.add(newAssessmentTextArea);
        panelNewAssessment.add(question1);
        panelNewAssessment.add(question2);
        panelNewAssessment.add(question3);
        panelNewAssessment.add(question4);
        panelNewAssessment.add(question5);
        panelNewAssessment.add(question6);
        panelNewAssessment.add(submitAssessment);
        String newAssessment1 = "Please answer the following questions the the best of your ability:\n";
        String newAssessment2 = "1 = strongly disagree, 2 = disagree, 3 = neutral, 4 = agree, 5 = strongly agree\n\n\n";
        String newAssessment3 = Assessment.QUESTION2C + "\n" + Assessment.QUESTION3A + "\n" +  Assessment.QUESTION4X;
        String newAssessment4 = Assessment.QUESTION5E + "\n" + Assessment.QUESTION6H + "\n" + Assessment.QUESTION7O;
        String fullAssessmentString = newAssessment1 + newAssessment2 + newAssessment3 + "\n" + newAssessment4;
        newAssessmentTextArea.setText(fullAssessmentString);
    }

    public void setupLoadSavePanel() {
        panelLoadSave.setLayout(new BoxLayout(panelLoadSave, BoxLayout.PAGE_AXIS));
        panelLoadSave.add(textLoadSave);
        panelLoadSave.add(buttonNew);
        panelLoadSave.add(buttonLoad);
    }

    public void setupExitSavePanel() {
        panelExitSave.setLayout(new BoxLayout(panelExitSave, BoxLayout.PAGE_AXIS));
        panelExitSave.add(textExitSave);
        panelExitSave.add(buttonNoSave);
        panelExitSave.add(buttonYesSave);
    }

    public void setupTeamButtonsPanel() {
        panelTeamsButtons.removeAll();
        for (Team team : yourApp.getTeamList()) {
            JButton buttonToAdd = new JButton(team.getTeamName());
            buttonToAdd.setName(team.getTeamName());
            buttonToAdd.addActionListener(this);
            panelTeamsButtons.add(buttonToAdd);
        }
    }

    public void setupUsersPanel(Team currentTeam) {
        panelUsersButtons.removeAll();
        for (User user : currentTeam.getUserList()) {
            JButton buttonToAdd = new JButton(user.getUserName());
            buttonToAdd.setName(user.getUserName());
            buttonToAdd.addActionListener(this);
            panelUsersButtons.add(buttonToAdd);
        }
    }

    public void actionPerformed(ActionEvent e) {
        final JComponent source = (JComponent) e.getSource();
        chooseLoad(source);
        chooseSave(source);
        chooseTeam(source);
        chooseUser(source);
        chooseGoBack(source);
        chooseNewTeamName(source);
        chooseNewUserName(source);
        chooseSubmitAssessment(source);

        if (source.equals(newAssessment)) {
            cl.show(panelContainer, "5");
        }

        if (source.equals(buttonEnd)) {
            cl.show(panelContainer, "6");
        }

        if (source.equals(removeUser)) {
            currentTeam.removeUser(currentUser);
            textUsers.setText("Current Users: \n" + currentTeam.toString());
            setupUsersPanel(currentTeam);
            cl.show(panelContainer, "3");
            playCrowdSound("Boo");
        }

    }

    public void chooseGoBack(JComponent theSource) {
        if (theSource.equals(backToUsers)) {
            textUsers.setText("Current Users: \n" + currentTeam.toString());
            setupUsersPanel(currentTeam);
            cl.show(panelContainer, "3");
        }

        if (theSource.equals(backToTeams)) {
            textTeams.setText("Current Teams: \n" + yourApp.toString());
            setupTeamButtonsPanel();
            cl.show(panelContainer, "2");
        }
    }

    public void chooseSubmitAssessment(JComponent theSource) {
        if (theSource.equals(submitAssessment)) {
            int answer1 = Integer.parseInt(question1.getText());
            int answer2 = Integer.parseInt(question2.getText());
            int answer3 = Integer.parseInt(question3.getText());
            int answer4 = Integer.parseInt(question4.getText());
            int answer5 = Integer.parseInt(question5.getText());
            int answer6 = Integer.parseInt(question6.getText());
            question1.setText("");
            question2.setText("");
            question3.setText("");
            question4.setText("");
            question5.setText("");
            question6.setText("");
            Assessment newAssessment = new Assessment(answer1, answer2, answer3, answer4, answer5, answer6);
            currentUser.addAssessment(newAssessment);
            textAssessments.setText("Current User: \n" + currentUser.toString());
            cl.show(panelContainer, "4");
        }
    }

    public void chooseNewTeamName(JComponent theSource) {
        if (theSource.equals(enterNewTeamName)) {
            String teamName = newTeamName.getText();
            newTeamName.setText("");
            currentTeam = yourApp.teamExists(teamName);
            setupUsersPanel(currentTeam);
            textUsers.setText("Current Team: \n" + currentTeam.toString());
            cl.show(panelContainer, "3");
        }
    }

    public void chooseNewUserName(JComponent theSource) {
        if (theSource.equals(enterNewUserName)) {
            String userName = newUserName.getText();
            newUserName.setText("");
            currentUser = currentTeam.userExists(userName);
            textAssessments.setText("Current User: \n" + currentUser.toString());
            cl.show(panelContainer, "4");
        }
    }

    public void chooseSave(JComponent theSource) {
        if (theSource.equals(buttonNoSave)) {
            System.exit(0);
        }
        if (theSource.equals(buttonYesSave)) {
            saveApp();
            System.exit(0);
        }
    }

    public void chooseLoad(JComponent theSource) {
        if (theSource.equals(buttonNew)) {
            yourApp = new App();
            cl.show(panelContainer, "2");
            setupTeamButtonsPanel();
            playCrowdSound("Cheer");
        }
        if (theSource.equals(buttonLoad)) {
            loadApp();
            textTeams.setText("Current Teams: \n" + yourApp.toString());
            cl.show(panelContainer, "2");
            setupTeamButtonsPanel();
            playCrowdSound("Cheer");
        }
    }

    public void chooseTeam(JComponent theSource) {
        for (Team team : yourApp.getTeamList()) {
            if (theSource.getName().equals(team.getTeamName())) {
                this.currentTeam = team;
                setupUsersPanel(team);
                textUsers.setText("Current Team: \n" + team.toString());
                cl.show(panelContainer, "3");
                playCrowdSound("Cheer");
            }
        }
    }

    //Modifies: this
    //Effects: Writes an App to a file
    public void chooseUser(JComponent theSource) {
        for (User user : currentTeam.getUserList()) {
            if (theSource.getName().equals(user.getUserName())) {
                this.currentUser = user;
                textAssessments.setText("Current User: \n" + user.toString());
                cl.show(panelContainer, "4");
            }
        }
    }

    //Modifies: this
    //Effects: Writes an App to a file
    // This class is based on the CPSC 210 Json Serialization Demo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void saveApp() {
        try {
            jsonWriter.open();
            jsonWriter.write(yourApp);
            jsonWriter.close();
            System.out.println("Saved data to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //Modifies: this
    //Effects: loads an App from a file
    // This method is based on the CPSC 210 Json Serialization Demo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void loadApp() {
        try {
            yourApp = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //Requires: the name of the sound to be played
    //Effects: loads and plays a Cheer or Boo sound from a file
    // This method is based on information provided by the Java API reference
    // https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/AudioSystem.html
    public void playCrowdSound(String soundName) {
        try {
            File file = new File("./data/" + soundName + ".wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
