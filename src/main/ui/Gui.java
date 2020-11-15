package ui;

import model.App;
import model.Team;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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

    JPanel panelUsers = new JPanel();
    JPanel panelUsersButtons = new JPanel();

    JPanel panelAssessments = new JPanel();

    JButton buttonNew = new JButton("No - Start Fresh");
    JButton buttonLoad = new JButton("Yes - Load Previous");
    JButton buttonEnd = new JButton("End Session");

    String textLoadSaveOne = "Welcome to the Digital HEXACO personality test.";
    String textLoadSaveTwo = "Would you like to load data from a previous session?";

    JTextArea textLoadSave = new JTextArea(textLoadSaveOne + "\n" + textLoadSaveTwo);
    JTextArea textTeams = new JTextArea();
    JTextArea textUsers = new JTextArea();

    CardLayout cl = new CardLayout();

    public Gui() {
        setupContainer();
        setupLoadSavePanel();

        panelTeams.setLayout(new BoxLayout(panelTeams, BoxLayout.PAGE_AXIS));
        panelTeams.add(textTeams);
        panelTeamsButtons.setLayout(new GridLayout(4,0));

        panelUsers.setLayout(new BoxLayout(panelUsers, BoxLayout.PAGE_AXIS));
        panelUsers.add(textUsers);
        panelUsersButtons.setLayout(new GridLayout(4,0));

//        panelContainer.add(panelLoadSave, "1");
//        panelContainer.add(panelTeams, "2");
//        panelContainer.add(panelUsers, "3");
//        panelContainer.add(panelAssessments, "4");

        buttonNew.addActionListener(this);
        buttonLoad.addActionListener(this);
        buttonEnd.addActionListener(this);

        buttonNew.setName("");
        buttonLoad.setName("");
        buttonEnd.setName("");

        frame.add(panelContainer);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public void setupContainer() {
        panelContainer.setLayout(cl);
        panelContainer.setPreferredSize(new Dimension(800,800));
        panelContainer.add(panelLoadSave, "1");
        panelContainer.add(panelTeams, "2");
        panelContainer.add(panelUsers, "3");
        panelContainer.add(panelAssessments, "4");
    }

    public void setupLoadSavePanel() {
        panelLoadSave.setLayout(new BoxLayout(panelLoadSave, BoxLayout.PAGE_AXIS));
        panelLoadSave.add(textLoadSave);
        panelLoadSave.add(buttonNew);
        panelLoadSave.add(buttonLoad);
    }


    public void setupTeamsPanel() {
        for (Team team : yourApp.getTeamList()) {
            JButton buttonToAdd = new JButton(team.getTeamName());
            buttonToAdd.setName(team.getTeamName());
            buttonToAdd.addActionListener(this);
            panelTeamsButtons.add(buttonToAdd);
        }
        panelTeamsButtons.add(buttonEnd);
        panelTeams.add(panelTeamsButtons);
    }

    public void setupUsersPanel(Team currentTeam) {
        for (User user : currentTeam.getUserList()) {
            JButton buttonToAdd = new JButton(user.getUserName());
            panelUsersButtons.add(buttonToAdd);
        }
        panelUsersButtons.add(buttonEnd);
        panelUsers.add(panelUsersButtons);
    }

    public void actionPerformed(ActionEvent e) {
        final JComponent source = (JComponent) e.getSource();
        if (source.equals(buttonNew)) {
            yourApp = new App();
            setupTeamsPanel();
        }

        chooseLoad(source);

        if (source.equals(buttonEnd)) {
            cl.show(panelContainer, "1");
        }

        chooseTeam(source);

        for (User user : currentTeam.getUserList()) {
            if (source.getName().equals(user.getUserName())) {
                this.currentUser = user;
                //setupUsersPanel(team);
                //textUsers.setText("Current Team: \n" + team.toString());
                cl.show(panelContainer, "4");
            }
        }
    }

    public void chooseLoad(JComponent theSource) {
        if (theSource.equals(buttonLoad)) {
            loadApp();
            textTeams.setText("Current Teams: \n" + yourApp.toString());
            cl.show(panelContainer, "2");
            setupTeamsPanel();
        }
    }

    public void chooseTeam(JComponent theSource) {
        for (Team team : yourApp.getTeamList()) {
            if (theSource.getName().equals(team.getTeamName())) {
                this.currentTeam = team;
                setupUsersPanel(team);
                textUsers.setText("Current Team: \n" + team.toString());
                cl.show(panelContainer, "3");
            }
        }
    }

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
    // This class is based on the CPSC 210 Json Serialization Demo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void loadApp() {
        try {
            yourApp = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public static void main(String[] args) {
        new Gui();
    }

}
