package com.uca.dao;

import com.uca.util.PropertiesReader;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class _Initializer
{
    public static void Init()
    {
        Connection connection = _Connector.getInstance();

        try
        {
            PreparedStatement statement;
            statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Student(" +
                    "   id_student BIGINT AUTO_INCREMENT," +
                    "   lastname VARCHAR(50) NOT NULL," +
                    "   firstname VARCHAR(50) NOT NULL," +
                    "   CONSTRAINT PK_Student PRIMARY KEY(id_student)" +
                    ");" +

                    "CREATE TABLE IF NOT EXISTS Sticker(" +
                    "   id_sticker BIGINT AUTO_INCREMENT," +
                    "   color VARCHAR(50) NOT NULL," +
                    "   description VARCHAR(50) NOT NULL," +
                    "   CONSTRAINT PK_Sticker PRIMARY KEY(id_sticker)," +
                    "   CONSTRAINT AK_Sticker UNIQUE(color, description)" + // unique combinations, no repeats
                    ");" +

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
                    // ...will need to deal with updating the corresponding values in the Award table
                    "   CONSTRAINT FK_Award_Sticker FOREIGN KEY(id_sticker) REFERENCES Sticker(id_sticker) ON DELETE CASCADE," +
                    "   CONSTRAINT FK_Award_Student FOREIGN KEY(id_student) REFERENCES Student(id_student) ON DELETE CASCADE" +
                    ");" +
                    "CREATE INDEX IF NOT EXISTS idx_attribution_date ON Award(attribution_date);"
                    // we will often sort them by date, and award will probably be the fastest-growing population in DB
            );
            statement.executeUpdate();

            // thanks to https://stackoverflow.com/questions/5307164/execute-insert-if-table-is-empty/5307178#5307178
            // in order to avoid duplicate inserts and SQLExceptions
            statement = connection.prepareStatement(
                    "INSERT INTO Teacher (firstname, lastname, username, userpwd, usersalt)" +
                    "SELECT ?, ?, ?, ?, ? " +
                    "FROM DUAL " +
                    "WHERE NOT EXISTS (SELECT * FROM Teacher WHERE username = ?);");
            statement.setString(1, "Robert");
            statement.setString(2, "Framboisier");
            statement.setString(3, "rob_fra");
            statement.setString(4, "jOfL5U6hBj/lhxrm8/XlOs+K0DbG1M7tC/ehW1Kzz4w=");
            statement.setString(5, "WNxq1r6Q2ZyA8AKQSYXJlg5XANMu9Kdp");
            statement.setString(6, "rob_fra");
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "INSERT INTO Teacher (firstname, lastname, username, userpwd, usersalt)" +
                    "SELECT ?, ?, ?, ?, ? " +
                    "FROM DUAL " +
                    "WHERE NOT EXISTS (SELECT * FROM Teacher WHERE username = ?);");
            statement.setString(1, "Charlotte");
            statement.setString(2, "Myrtille");
            statement.setString(3, "cha_myr");
            statement.setString(4, "R8ld7acSBTxT23oR7IHB+hR/blrP6SD7/8Ja6gh7aEg=");
            statement.setString(5, "tBtlrrVWRfFpGntfYuWcX3KwxrtRuq6h");
            statement.setString(6, "cha_myr");
            statement.executeUpdate();

            System.out.println("~~~~~~~~~~" +
                               "\nHello! Please open a browser at: " +
                               "\nlocalhost:" + Integer.parseInt(new PropertiesReader().getProperty("port")) +
                               "\n~~~~~~~~~~");
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("could not create database !");
        }
    }
}
