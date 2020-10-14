package model;

import java.util.ArrayList;

public class User {
    private String userName;
    private ArrayList<Test> tests = new ArrayList<>();

    public User(String name) {
        this.userName = name;
    }

    public void addTest(Test test) {
        this.tests.add(test);
    }
}
