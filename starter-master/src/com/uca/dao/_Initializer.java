package com.uca.dao;

import java.sql.*;

import static com.uca.util.DateUtil.getSQLDateRelativeToToday;

public class _Initializer
{
    public static void Init()
    {
        Connection connection = _Connector.getInstance();

        try
        {
            PreparedStatement statement;
            statement = connection.prepareStatement(
                    "DROP TABLE IF EXISTS Student CASCADE;" +
                    // temporary, would be removed when using a real DB for prod
                    "CREATE TABLE IF NOT EXISTS Student(" +
                    "   id_student BIGINT AUTO_INCREMENT," +
                    "   lastname VARCHAR(50) NOT NULL," +
                    "   firstname VARCHAR(50) NOT NULL," +
                    "   CONSTRAINT PK_Student PRIMARY KEY(id_student)" +
                    ");" +
                    "DROP TABLE IF EXISTS Sticker CASCADE;" +
                    // temporary, would be removed when using a real DB for prod
                    "CREATE TABLE IF NOT EXISTS Sticker(" +
                    "   id_sticker BIGINT AUTO_INCREMENT," +
                    "   color VARCHAR(50) NOT NULL," +
                    "   description VARCHAR(50) NOT NULL," +
                    "   CONSTRAINT PK_Sticker PRIMARY KEY(id_sticker)" +
                    ");" +
                    "DROP TABLE IF EXISTS Teacher CASCADE;" +
                    // temporary, would be removed when using a real DB for prod
                    "CREATE TABLE IF NOT EXISTS Teacher(" +
                    "   id_teacher BIGINT AUTO_INCREMENT," +
                    "   lastname VARCHAR(50) NOT NULL," +
                    "   firstname VARCHAR(50) NOT NULL," +
                    "   username VARCHAR(50) NOT NULL," +
                    "   userpwd VARCHAR(50) NOT NULL," +
                    "   usersalt VARCHAR(50) NOT NULL," +
                    "   CONSTRAINT PK_Teacher PRIMARY KEY(id_teacher)," +
                    "   CONSTRAINT AK_Teacher UNIQUE(username)" +
                    ");" +
                    "DROP TABLE IF EXISTS Award;" + // temporary, would be removed when using a real DB for prod
                    "CREATE TABLE IF NOT EXISTS Award(" +
                    "   id_award BIGINT AUTO_INCREMENT," +
                    "   id_teacher BIGINT," +
                    "   id_sticker BIGINT NOT NULL," +
                    "   id_student BIGINT NOT NULL," +
                    "   attribution_date DATE NOT NULL," +
                    "   motive TEXT NOT NULL," +
                    "   CONSTRAINT PK_Award PRIMARY KEY(id_award)," +
                    "   CONSTRAINT FK_Award_Teacher FOREIGN KEY(id_teacher) REFERENCES Teacher(id_teacher)," +
                    "   CONSTRAINT FK_Award_Sticker FOREIGN KEY(id_sticker) REFERENCES Sticker(id_sticker) ON DELETE CASCADE," +
                    "   CONSTRAINT FK_Award_Student FOREIGN KEY(id_student) REFERENCES Student(id_student) ON DELETE CASCADE" +
                    ");"
            );

            statement.executeUpdate();

            //TODO teacher.delete -> update id_teacher to NULL for the awards they gave
            //sticker.delete -> delete corresponding awards
            //student.delete -> delete their awards

            statement = connection.prepareStatement(
                    "INSERT INTO Teacher(firstname, lastname, username, userpwd, usersalt) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1, "Robert");
            statement.setString(2, "Framboisier");
            statement.setString(3, "rob_fra");
            statement.setString(4, "jOfL5U6hBj/lhxrm8/XlOs+K0DbG1M7tC/ehW1Kzz4w=");
            statement.setString(5, "WNxq1r6Q2ZyA8AKQSYXJlg5XANMu9Kdp");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Teacher(firstname, lastname, username, userpwd, usersalt) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1, "Charlotte");
            statement.setString(2, "Myrtille");
            statement.setString(3, "cha_myr");
            statement.setString(4, "R8ld7acSBTxT23oR7IHB+hR/blrP6SD7/8Ja6gh7aEg=");
            statement.setString(5, "tBtlrrVWRfFpGntfYuWcX3KwxrtRuq6h");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Sticker(color, description) VALUES(?, ?);");
            statement.setString(1, "GREEN");
            statement.setString(2, "INDIVIDUAL_SERVICE");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Sticker(color, description) VALUES(?, ?);");
            statement.setString(1, "RED");
            statement.setString(2, "OTHER_BAD_CONDUCT");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Sticker(color, description) VALUES(?, ?);");
            statement.setString(1, "WHITE");
            statement.setString(2, "COMMUNITY_SERVICE");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Student(lastname, firstname) VALUES(?, ?);");
            statement.setString(1, "Woldemichael");
            statement.setString(2, "Eyosias");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Student(lastname, firstname) VALUES(?, ?);");
            statement.setString(1, "Doe");
            statement.setString(2, "John");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Student(lastname, firstname) VALUES(?, ?);");
            statement.setString(1, "Drai");
            statement.setString(2, "Alexis");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Award(id_teacher, id_sticker, id_student, attribution_date, motive) VALUES(?, ?, ?, ?, ?);");
            statement.setLong(1, 1);
            statement.setLong(2, 2);
            statement.setLong(3, 3);
            statement.setDate(4, getSQLDateRelativeToToday(-5));
            statement.setString(5, "a cod&eacute; avec ses lunettes de soleil");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Award(id_teacher, id_sticker, id_student, attribution_date, motive) VALUES(?, ?, ?, ?, ?);");
            statement.setLong(1, 2);
            statement.setLong(2, 3);
            statement.setLong(3, 1);
            statement.setDate(4, getSQLDateRelativeToToday(-9));
            statement.setString(5, "a aid&eacute; &agrave; ranger les chaises");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Award(id_teacher, id_sticker, id_student, attribution_date, motive) VALUES(?, ?, ?, ?, ?);");
            statement.setLong(1, 1);
            statement.setLong(2, 1);
            statement.setLong(3, 2);
            statement.setDate(4, getSQLDateRelativeToToday(-7));
            statement.setString(5, "a aid&eacute; un camarade &agrave; faire tourner Gradle sur son poste");
            statement.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("could not create database !");
        }
    }
}
