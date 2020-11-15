package ui;

import model.App;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Gui extends Frame implements ActionListener {
    private static final String JSON_STORE = "./data/App.json";
    private App yourApp;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    JFrame frame = new JFrame();
    JPanel panelContainer = new JPanel();
    JPanel panelLoadSave = new JPanel(new GridBagLayout());
    JPanel panelTeams = new JPanel(new GridBagLayout());
    JPanel panelUsers = new JPanel(new GridBagLayout());
    JPanel panelAssessments = new JPanel(new GridBagLayout());

    JButton buttonNew = new JButton("No - Start Fresh");
    JButton buttonLoad = new JButton("Yes - Load Previous");
    JButton buttonSecond = new JButton("Back");

    String textLoadSaveOne = "Welcome to the Digital HEXACO personality test.";
    String textLoadSaveTwo = "Would you like to load data from a previous session (y/n)?";

    JTextArea textLoadSave = new JTextArea(textLoadSaveOne + "\n" + textLoadSaveTwo);
    JTextArea textTeams = new JTextArea();

    CardLayout cl = new CardLayout();

    public Gui() {
        panelContainer.setLayout(cl);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;

        panelTeams.add(textTeams);
        panelLoadSave.add(buttonNew);
        panelLoadSave.add(buttonLoad);
        panelTeams.add(buttonSecond);
        panelContainer.add(panelLoadSave, "1");
        panelContainer.add(panelTeams, "2");
        panelContainer.add(panelUsers, "3");
        panelContainer.add(panelAssessments, "3");

//        cl.show(panelContainer, "1");

        buttonNew.addActionListener(this);
        buttonLoad.addActionListener(this);
        buttonSecond.addActionListener(this);

        panelLoadSave.add(textLoadSave);

        frame.add(panelContainer);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }



    public void actionPerformed(ActionEvent e) {
        final JComponent source = (JComponent) e.getSource();
        if (source.equals(buttonNew)) {
            yourApp = new App();
        }
        if (source.equals(buttonLoad)) {
            loadApp();
            textTeams.setText(yourApp.toString());
            cl.show(panelContainer, "2");
        }

        if (source.equals(buttonSecond)) {
            cl.show(panelContainer, "1");
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
