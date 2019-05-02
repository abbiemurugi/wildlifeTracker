## Title

Wildlife-Tracker Java Application


## Author

Abigail Murugi


## Description

It is a Java Application that allows Rangers to track wildlife sightings in the area,

that also keep track of endangered animals in the forest.


## Project SetUp

If you want to use this as your template, here's how to go about it:

Install Git on you machine

Maneuver to 'clone or download' button and get the link

--Linux Users-- open your terminal and run the 'git clone ...' command in a directory of your choice

--for Windows Users-- Navigate to the location you'd want the repository located, right click and select "git bash here"

Clone the repository

Upon completion, navigate to the cloned repository Feel free to edit the files to your prefered taste

Now build the Database to enable storing of created instances

Install Postgres SQL

Run psql command to access DataBase

then run the following commands in your terminal

In PSQL:

CREATE DATABASE wildlife-tracker;

CREATE TABLE Animal (id serial PRIMARY KEY, name varchar, health varchar, age int);

CREATE TABLE Sighting (id serial PRIMARY KEY, name varchar, location varchar, rangerName varchar, animalId int);

CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

## Technologies Used

* IntelliJ IDEA version 8

* Terminal

* Gradle build

* DataBase

# License

MIT license
