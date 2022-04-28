package com.uca.dao;

import com.uca.entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO extends _Generic<UserEntity>
{
    // CRUD Create Read (Update) Delete

    // TODO ? Read one? Read some?

    // Read all

    public ArrayList<UserEntity> getAllUsers()
    {
        ArrayList<UserEntity> entities = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = this.connect.prepareStatement(
                    "SELECT * FROM Teacher ORDER BY lastname;");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                UserEntity entity = new UserEntity();
                entity.setId_teacher(resultSet.getInt("id_teacher"));
                entity.setFirstName(resultSet.getString("firstname"));
                entity.setLastName(resultSet.getString("lastname"));
                entity.setUserName(resultSet.getString("username"));
                entity.setUserPwd(resultSet.getString("userpwd"));

                entities.add(entity);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return entities;
    }

    // TODO redo with CM slides and security in mind
    // TODO decide whether to keep ID in the code or leave it in the database

    /**
     * creates a user entity in database
     *
     * @param obj instance of the basic UserEntity class
     */
    @Override
    public UserEntity create(UserEntity obj)
    {
        int    id_teacher = obj.getId_teacher();
        String fName      = obj.getFirstName();
        String lName      = obj.getLastName();
        String uName      = obj.getUserName();
        String uPwd       = obj.getUserPwd();
        String query = String.format(
                "INSERT INTO users (id_teacher, lastname, firstname, username, userpwd) (%d, %s, %s, %s, %s);",
                id_teacher,
                fName,
                lName,
                uName,
                uPwd);

        try
        {
            PreparedStatement preparedStatement = this.connect.prepareStatement(query);
            preparedStatement.executeQuery();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(UserEntity obj)
    {
        //TODO !
    }
}
