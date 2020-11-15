package ui;

import model.App;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gui extends Frame implements ActionListener {
    private static final String JSON_STORE = "./data/App.json";
    private App yourApp = new App();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ArrayList<JButton> teamButtons;


    JFrame frame = new JFrame();
    JPanel panelContainer = new JPanel();
    JPanel panelLoadSave = new JPanel();
    JPanel panelTeams = new JPanel();

    JPanel panelTeamsButtons = new JPanel();

    JPanel panelUsers = new JPanel();
    JPanel panelAssessments = new JPanel();

    JButton buttonNew = new JButton("No - Start Fresh");
    JButton buttonLoad = new JButton("Yes - Load Previous");
    JButton buttonSecond = new JButton("End Session");

    String textLoadSaveOne = "Welcome to the Digital HEXACO personality test.";
    String textLoadSaveTwo = "Would you like to load data from a previous session?";

    JTextArea textLoadSave = new JTextArea(textLoadSaveOne + "\n" + textLoadSaveTwo);
    JTextArea textTeams = new JTextArea();

    CardLayout cl = new CardLayout();

    public Gui() {
        panelContainer.setLayout(cl);
        panelContainer.setPreferredSize(new Dimension(800,800));

        panelLoadSave.setLayout(new BoxLayout(panelLoadSave, BoxLayout.PAGE_AXIS));
        panelLoadSave.add(textLoadSave);
        panelLoadSave.add(buttonNew);
        panelLoadSave.add(buttonLoad);

        panelTeams.setLayout(new BoxLayout(panelTeams, BoxLayout.PAGE_AXIS));
        panelTeams.add(textTeams);

        panelTeamsButtons.setLayout(new GridLayout(4,0));

        panelContainer.add(panelLoadSave, "1");
        panelContainer.add(panelTeams, "2");
        panelContainer.add(panelUsers, "3");
        panelContainer.add(panelAssessments, "3");

        buttonNew.addActionListener(this);
        buttonLoad.addActionListener(this);
        buttonSecond.addActionListener(this);

        frame.add(panelContainer);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public void setupTeamsPanel() {
        for (Team team : yourApp.getTeamList()) {
            JButton buttonToAdd = new JButton(team.getTeamName());
            buttonToAdd.setName(team.getTeamName());
            panelTeamsButtons.add(buttonToAdd);
        }
        panelTeamsButtons.add(buttonSecond);
        panelTeams.add(panelTeamsButtons);

    }

    public void setupTeamPanel() {
        for (Team team : yourApp.getTeamList()) {
            JButton buttonToAdd = new JButton(team.getTeamName());
            panelTeamsButtons.add(buttonToAdd);
        }
        panelTeamsButtons.add(buttonSecond);
        panelTeams.add(panelTeamsButtons);

    }



    public void actionPerformed(ActionEvent e) {
        final JComponent source = (JComponent) e.getSource();
        if (source.equals(buttonNew)) {
            yourApp = new App();
            setupTeamsPanel();
        }
        if (source.equals(buttonLoad)) {
            loadApp();
            textTeams.setText("Current Teams: \n" + yourApp.toString());
            cl.show(panelContainer, "2");
            setupTeamsPanel();
        }
        if (source.equals(buttonSecond)) {
            cl.show(panelContainer, "1");
        }

        for (Team team : yourApp.getTeamList()) {
            if (source.getName().equals(team.getTeamName())) {
                cl.show(panelContainer, "3");
            }
        }
    }




    public static void main(String[] args) {
        new Gui();
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
}
