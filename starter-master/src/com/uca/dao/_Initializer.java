package com.uca.dao;

import java.sql.*;

public class _Initializer
{

    public static void Init()
    {
        Connection connection = _Connector.getInstance();

        try
        {
            PreparedStatement statement;
            statement = connection.prepareStatement(
                    "DROP TABLE IF EXISTS Pupil CASCADE;" + // temporary
                    "CREATE TABLE IF NOT EXISTS Pupil(" +
                    "   id_pupil INT AUTO_INCREMENT," +
                    "   lastname VARCHAR(50) NOT NULL," +
                    "   firstname VARCHAR(50) NOT NULL," +
                    "   CONSTRAINT PK_Pupil PRIMARY KEY(id_pupil)" +
                    ");" +
                    "DROP TABLE IF EXISTS Sticker CASCADE;" + // temporary
                    "CREATE TABLE IF NOT EXISTS Sticker(" +
                    "   id_sticker INT AUTO_INCREMENT," +
                    "   color VARCHAR(20) NOT NULL, /* Only within {GREEN, WHITE, RED}*/" +
                    "   description VARCHAR(20) NOT NULL, /* Only within {STAR, TRIANGLE, CIRCLE, SQUARE} (???)*/" +
                    "   CONSTRAINT PK_Sticker PRIMARY KEY(id_sticker)" +
                    ");" +
                    "DROP TABLE IF EXISTS Teacher CASCADE;" + // temporary
                    "CREATE TABLE IF NOT EXISTS Teacher(" +
                    "   id_teacher INT AUTO_INCREMENT," +
                    "   lastname VARCHAR(50) NOT NULL," +
                    "   firstname VARCHAR(50) NOT NULL," +
                    "   username VARCHAR(50) NOT NULL," +
                    "   userpwd VARCHAR(50) NOT NULL," +
                    "   CONSTRAINT PK_Teacher PRIMARY KEY(id_teacher)," +
                    "   CONSTRAINT AK_Teacher UNIQUE(username)" +
                    ");" +
                    "DROP TABLE IF EXISTS Award;" + // temporary
                    "CREATE TABLE IF NOT EXISTS Award(" +
                    "   id_teacher INT," +
                    "   id_sticker INT," +
                    "   id_pupil INT," +
                    "   attribution_date DATE NOT NULL," +
                    "   motive TEXT NOT NULL," +
                    "   CONSTRAINT PK_Award PRIMARY KEY(id_teacher, id_sticker, id_pupil)," +
                    "   CONSTRAINT FK_Award_Teacher FOREIGN KEY(id_teacher) REFERENCES Teacher(id_teacher) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "   CONSTRAINT FK_Award_Sticker FOREIGN KEY(id_sticker) REFERENCES Sticker(id_sticker) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "   CONSTRAINT FK_Award_Pupil FOREIGN KEY(id_pupil) REFERENCES Pupil(id_pupil) ON UPDATE CASCADE ON DELETE CASCADE" +
                    ");"
            );

            // TODO
            // instead of ON DELETE CASCADE, establish 'empty' values (or use NULL values?)
            // N/A...
            // gone...
            // no longer here...

            statement.executeUpdate();

            //Todo Remove me ! + Research Java.sql

            statement = connection.prepareStatement("DELETE FROM Teacher;"); // temporary, for testing
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Teacher(id_teacher, firstname, lastname, username, userpwd) VALUES(?, ?, ?, ?, ?);");
            statement.setInt(1, 1);
            statement.setString(2, "Theodore");
            statement.setString(3, "Muillerez");
            statement.setString(4, "theodore_muillerez");
            statement.setString(5, "tyjSERTJDRUJQEjzujequ6aqe8uque6ueU");//TODO do real password stuff?
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Teacher(id_teacher, firstname, lastname, username, userpwd) VALUES(?, ?, ?, ?, ?);");
            statement.setInt(1, 2);
            statement.setString(2, "Herman");
            statement.setString(3, "Mellville");
            statement.setString(4, "herman_melville");
            statement.setString(5, "izguebzeg7g5zezegseflmzZfaU");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Teacher(id_teacher, firstname, lastname, username, userpwd) VALUES(?, ?, ?, ?, ?);");
            statement.setInt(1, 3);
            statement.setString(2, "George");
            statement.setString(3, "Schefflera");
            statement.setString(4, "george_schefflera");
            statement.setString(5, "rhbJNYTrtyrty564tyrj6yj!yt");
            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM Sticker;"); // temporary, for testing
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Sticker(id_sticker, color, description) VALUES(?, ?, ?);");
            statement.setInt(1, 1);
            statement.setString(2, "GREEN");
            statement.setString(3, "TRIANGLE");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Sticker(id_sticker, color, description) VALUES(?, ?, ?);");
            statement.setInt(1, 2);
            statement.setString(2, "RED");
            statement.setString(3, "CIRCLE");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Sticker(id_sticker, color, description) VALUES(?, ?, ?);");
            statement.setInt(1, 3);
            statement.setString(2, "WHITE");
            statement.setString(3, "STAR");
            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM Pupil;"); // temporary, for testing
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Pupil(id_pupil, lastname, firstname) VALUES(?, ?, ?);");
            statement.setInt(1, 1);
            statement.setString(2, "Eyosias");
            statement.setString(3, "Woldemichael");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Pupil(id_pupil, lastname, firstname) VALUES(?, ?, ?);");
            statement.setInt(1, 2);
            statement.setString(2, "Yacine");
            statement.setString(3, "Ayachi");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Pupil(id_pupil, lastname, firstname) VALUES(?, ?, ?);");
            statement.setInt(1, 3);
            statement.setString(2, "Alexis");
            statement.setString(3, "Drai");
            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM Award;"); // temporary, for testing
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Award(id_teacher, id_sticker, id_pupil, attribution_date, motive) VALUES(?, ?, ?, ?, ?);");
            statement.setInt(1, 1);
            statement.setInt(2, 2);
            statement.setInt(3, 3);
            statement.setDate(4, new Date(new java.util.Date().getTime()));
            // gets the number of ms since 01/01/1970 and feeds it to the javasql date object
            statement.setString(5, "some random motive");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Award(id_teacher, id_sticker, id_pupil, attribution_date, motive) VALUES(?, ?, ?, ?, ?);");
            statement.setInt(1, 2);
            statement.setInt(2, 3);
            statement.setInt(3, 1);
            statement.setDate(4, new Date(new java.util.Date().getTime()));
            // gets the number of ms since 01/01/1970 and feeds it to the javasql date object
            statement.setString(5, "some other random motive");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Award(id_teacher, id_sticker, id_pupil, attribution_date, motive) VALUES(?, ?, ?, ?, ?);");
            statement.setInt(1, 3);
            statement.setInt(2, 1);
            statement.setInt(3, 2);
            statement.setDate(4, new Date(new java.util.Date().getTime()));
            // gets the number of ms since 01/01/1970 and feeds it to the javasql date object
            statement.setString(5, "some other other random motive");
            statement.executeUpdate();

        } catch (Exception e)
        {
            System.out.println(e.toString());
            throw new RuntimeException("could not create database !");
        }
    }
}
