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
                    "   id_pupil BIGINT AUTO_INCREMENT," +
                    "   lastname VARCHAR(50) NOT NULL," +
                    "   firstname VARCHAR(50) NOT NULL," +
                    "   CONSTRAINT PK_Pupil PRIMARY KEY(id_pupil)" +
                    ");" +
                    "DROP TABLE IF EXISTS Sticker CASCADE;" + // temporary
                    "CREATE TABLE IF NOT EXISTS Sticker(" +
                    "   id_sticker BIGINT AUTO_INCREMENT," +
                    "   color VARCHAR(20) NOT NULL, /* Only within {GREEN, WHITE, RED}*/" +
                    "   description VARCHAR(20) NOT NULL, /* Only within {STAR, TRIANGLE, CIRCLE, SQUARE} (???)*/" +
                    "   CONSTRAINT PK_Sticker PRIMARY KEY(id_sticker)" +
                    ");" +
                    "DROP TABLE IF EXISTS Teacher CASCADE;" + // temporary
                    "CREATE TABLE IF NOT EXISTS Teacher(" +
                    "   id_teacher BIGINT AUTO_INCREMENT," +
                    "   lastname VARCHAR(50) NOT NULL," +
                    "   firstname VARCHAR(50) NOT NULL," +
                    "   username VARCHAR(50) NOT NULL," +
                    "   userHpwd VARCHAR(50) NOT NULL," +
                    "   userSpwd VARCHAR(50) NOT NULL," +
                    "   CONSTRAINT PK_Teacher PRIMARY KEY(id_teacher)," +
                    "   CONSTRAINT AK_Teacher UNIQUE(username)" +
                    ");" +
                    "DROP TABLE IF EXISTS Award;" + // temporary
                    "CREATE TABLE IF NOT EXISTS Award(" +
                    "   id_teacher BIGINT," +
                    "   id_sticker BIGINT," +
                    "   id_pupil BIGINT," +
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
                    "INSERT INTO Teacher(firstname, lastname, username, userHpwd, userSpwd) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1, "Robert");
            statement.setString(2, "Framb");
            statement.setString(3, "robert_framb");
            statement.setString(4, "sA0jNGQTrAfMUiqrB++bMKTU55ThdFCl16ZZTIXwD2M=");
            statement.setString(5, "n7d9MPQFXxDqzT6onmong3hQt8Nyko");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Teacher(firstname, lastname, username, userHpwd, userSpwd) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1, "Charlotte");
            statement.setString(2, "Myrtille");
            statement.setString(3, "charlotte_myrtille");
            statement.setString(4, "izguebzeg7g5zezegseflmzZfaU");
            statement.setString(5, "n7d9MPQFXxDqzT6onmong3hQt8Nyko");
            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM Sticker;"); // temporary, for testing
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Sticker(color, description) VALUES(?, ?);");
            statement.setString(1, "GREEN");
            statement.setString(2, "TRIANGLE");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Sticker(color, description) VALUES(?, ?);");
            statement.setString(1, "RED");
            statement.setString(2, "CIRCLE");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Sticker(color, description) VALUES(?, ?);");
            statement.setString(1, "WHITE");
            statement.setString(2, "STAR");
            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM Pupil;"); // temporary, for testing
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Pupil(lastname, firstname) VALUES(?, ?);");
            statement.setString(1, "Eyosias");
            statement.setString(2, "Woldemichael");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Pupil(lastname, firstname) VALUES(?, ?);");
            statement.setString(1, "Yacine");
            statement.setString(2, "Ayachi");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Pupil(lastname, firstname) VALUES(?, ?);");
            statement.setString(1, "Alexis");
            statement.setString(2, "Drai");
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
            statement.setInt(1, 1);
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
