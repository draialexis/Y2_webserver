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

            //Init articles table
            statement = connection.prepareStatement("DROP TABLE IF EXISTS Pupil;" +
                                                    "CREATE TABLE Pupil(" +
                                                    "   id_pupil INT AUTO_INCREMENT," +
                                                    "   lastname VARCHAR(50) NOT NULL," +
                                                    "   firstname VARCHAR(50) NOT NULL," +
                                                    "   CONSTRAINT PK_Pupil PRIMARY KEY(id_pupil)" +
                                                    ");" +
                                                    "DROP TABLE IF EXISTS Sticker;" +
                                                    "CREATE TABLE Sticker(" +
                                                    "   id_sticker INT AUTO_INCREMENT," +
                                                    "   color VARCHAR(20) NOT NULL, /* Only within {GREEN, WHITE, RED}*/" +
                                                    "   description VARCHAR(20) NOT NULL, /* Only within {STAR, TRIANGLE, CIRCLE, SQUARE} (???)*/" +
                                                    "   CONSTRAINT PK_Sticker PRIMARY KEY(id_sticker)" +
                                                    ");" +
                                                    "DROP TABLE IF EXISTS Teacher;" +
                                                    "CREATE TABLE Teacher(" +
                                                    "   id_teacher INT AUTO_INCREMENT," +
                                                    "   lastname VARCHAR(50) NOT NULL," +
                                                    "   firstname VARCHAR(50) NOT NULL," +
                                                    "   username VARCHAR(50) NOT NULL," +
                                                    "   userpwd VARCHAR(50) NOT NULL," +
                                                    "   CONSTRAINT PK_Teacher PRIMARY KEY(id_teacher)," +
                                                    "   CONSTRAINT AK_Teacher UNIQUE(username)" +
                                                    ");" +
                                                    "DROP TABLE IF EXISTS Award;" +
                                                    "CREATE TABLE Award(" +
                                                    "   id_teacher INT," +
                                                    "   id_sticker INT," +
                                                    "   id_pupil INT," +
                                                    "   attribution_date DATE NOT NULL," +
                                                    "   motive TEXT NOT NULL," +
                                                    "   CONSTRAINT PK_Award PRIMARY KEY(id_teacher, id_sticker, id_pupil)," +
                                                    "   CONSTRAINT FK_Award_Teacher FOREIGN KEY(id_teacher) REFERENCES Teacher(id_teacher)," +
                                                    "   CONSTRAINT FK_Award_Sticker FOREIGN KEY(id_sticker) REFERENCES Sticker(id_sticker)," +
                                                    "   CONSTRAINT FK_Award_Pupil FOREIGN KEY(id_pupil) REFERENCES Pupil(id_pupil)" +
                                                    ");");
            statement.executeUpdate();

            //Todo Remove me !
            statement = connection.prepareStatement("INSERT INTO Teacher(firstname, lastname, username, userpwd) VALUES(?, ?, ?, ?);");
            statement.setString(1, "Theodore");
            statement.setString(2, "Muillerez");
            statement.setString(3, "theodore_muillerez");
            statement.setString(4, "tyjSERTJDRUJQEjzujeq(u6aqe8uque6ueU");//TODO do real password stuff?
            statement.executeUpdate();

        } catch (Exception e)
        {
            System.out.println(e.toString());
            throw new RuntimeException("could not create database !");
        }
    }
}
