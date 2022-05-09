package com.uca.dao;

import com.uca.entity.Color;
import com.uca.entity.Description;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
                    // teachers cannot get deleted at the moment. if that changes, TeacherDAO.delete...() method(s)
                    // will need to deal with updating the corresponding values in the Award table
                    "   CONSTRAINT FK_Award_Sticker FOREIGN KEY(id_sticker) REFERENCES Sticker(id_sticker) ON DELETE CASCADE," +
                    "   CONSTRAINT FK_Award_Student FOREIGN KEY(id_student) REFERENCES Student(id_student) ON DELETE CASCADE" +
                    ");"
            );

            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Teacher(firstname, lastname, username, userpwd, usersalt)" +
                    "VALUES(?, ?, ?, ?, ?)," +
                    "(?, ?, ?, ?, ?);");
            statement.setString(1, "Robert");
            statement.setString(2, "Framboisier");
            statement.setString(3, "rob_fra");
            statement.setString(4, "jOfL5U6hBj/lhxrm8/XlOs+K0DbG1M7tC/ehW1Kzz4w=");
            statement.setString(5, "WNxq1r6Q2ZyA8AKQSYXJlg5XANMu9Kdp");
            statement.setString(6, "Charlotte");
            statement.setString(7, "Myrtille");
            statement.setString(8, "cha_myr");
            statement.setString(9, "R8ld7acSBTxT23oR7IHB+hR/blrP6SD7/8Ja6gh7aEg=");
            statement.setString(10, "tBtlrrVWRfFpGntfYuWcX3KwxrtRuq6h");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Sticker(color, description) " +
                    "VALUES(?, ?)," +
                    "(?, ?)," +
                    "(?, ?);");
            statement.setString(1, Color.GREEN.name());
            statement.setString(2, Description.INDIVIDUAL_SERVICE.name());
            statement.setString(3, Color.RED.name());
            statement.setString(4, Description.OTHER_BAD_CONDUCT.name());
            statement.setString(5, Color.WHITE.name());
            statement.setString(6, Description.COMMUNITY_SERVICE.name());
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Student(lastname, firstname) " +
                    "VALUES(?, ?)," +
                    "(?, ?)," +
                    "(?, ?);");
            statement.setString(1, "Woldemichael");
            statement.setString(2, "Eyosias");
            statement.setString(3, "Doe");
            statement.setString(4, "John");
            statement.setString(5, "Drai");
            statement.setString(6, "Alexis");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Award(id_teacher, id_sticker, id_student, attribution_date, motive) " +
                    "VALUES(?, ?, ?, ?, ?)," +
                    "(?, ?, ?, ?, ?)," +
                    "(?, ?, ?, ?, ?);");
            statement.setLong(1, 1);
            statement.setLong(2, 2);
            statement.setLong(3, 3);
            statement.setDate(4, getSQLDateRelativeToToday(-5));
            statement.setString(5, "a cod&eacute; avec ses lunettes de soleil");
            statement.setLong(6, 2);
            statement.setLong(7, 3);
            statement.setLong(8, 1);
            statement.setDate(9, getSQLDateRelativeToToday(-9));
            statement.setString(10, "a aid&eacute; &agrave; ranger les chaises");
            statement.setLong(11, 1);
            statement.setLong(12, 1);
            statement.setLong(13, 2);
            statement.setDate(14, getSQLDateRelativeToToday(-7));
            statement.setString(15, "a aid&eacute; un camarade &agrave; faire tourner Gradle sur son poste");
            statement.executeUpdate();

        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("could not create database !");
        }
    }
}
