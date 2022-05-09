package com.uca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class _Connector
{
    private static Connection connect;

    public static Connection getInstance()
    {
        if (connect == null)
        {
            try
            {
                connect = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return connect;
    }
}
