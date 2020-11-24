# HEXACO: A digital personality assessment
## Introduction

This project will essentially be a digital personality assessment for the purposes of effective team formation.
Users will answer a series of questions and receive an evaluation on their personality. Users will be able to create 
profiles, allowing them to take the multiple times to see how their personality might change overtime. 

The assessment will be the **HEXACO** personality inventory, which rates subjects on 6 measures:
- Honesty-Humility
- Emotionality
- Extraversion
- Agreeableness
- Conscientiousness
- Openness to Experience

This application could be used by employers and teams to assign roles based on personality. It can also be 
used by anyone who is curious about themselves.

This project is of interest to me as I graduated with a degree in neuroscience last year, 
hope to connect my prior interests with computer science.

## User Stories
-As a user, I want to be able to create a team (Done)
-As a user, I want to be able to select a team and view the team members (Done)
-As a user, I want to be able to add as many users (team members) as I want to a team (Done)
-As a user, I want to be able to take a personality assessment, and have that data associated with me (Done)
-As a user, I want to be able to have multiple tests associated with me (Done)
-As a user, I want to be able to view the tests associated with me (Done)

## Phase 2 User Stories
-As a user, I want to be able to save all team, user, and assessment data to a file for later use (Done)
-As a user, I want to be able to reload all the data from a previous session from a file (Done)

## Phase 3 User Stories
-As a user, I want to be able to remove a member from a team (Done)
-As a user, I want to be able to do above stories using a graphical user interface (Done)

## Phase 4 User Stories (Phase 4: Task 2)
-Implemented a bi-directional association between Teams and Users (Done)
-Each User can be associated with just one Team

-When attempting to add a new User to a Team, check to see if an equal User is already in that Team
-If so, access the existing Team's data

-If Team does not already have an equal user, check to see if an equal User exists within the App (in other Teams)
-If the User already exists in a different Team, remove the User from the Team and add it to the requested Team

-If the User does not exist within any Teams, add the new User to the requested Team

-Users with the same userName are considered equal

-Team.userExists: Used to identify whether a User already exists within a Team
        - as well as whether a User already exists in other Teams within the overall App
        - Returns a reference to the desired User
        
-Team.addUser: Checks if a User has already been added to a Team
        - if not, adds the User to the Team
        - calls User.addToTeam to add the Team to the User
        
-Team.removeUser: If the Team contains the User, removes the User from the Team
        - calls User.removeTeam to remove the Team from the User

-User.addToTeam: Checks if a Team has already been associated with a User
        - if not, adds the Team to the User
        - calls Team.addUser to add the User to the Team
        
-User.removeTeam: If the User is associated with the Team, removes the Team from a User
        - calls Team.removeUser to remove the User from the Team        