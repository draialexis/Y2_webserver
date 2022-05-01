package com.uca.dao;

import com.uca.entity.TeacherEntity;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.util.ArrayList;

public class TeacherDAO extends _Generic<TeacherEntity>
{
    // TODO redo with CM slides and security in mind?
    // TODO decide whether to keep ID in the code or leave it in the database

    @Override
    public TeacherEntity create(TeacherEntity obj)
    {
        String fName = obj.getFirstName();
        String lName = obj.getLastName();
        String uName = obj.getUserName();
        String uPwd  = obj.getUserPwd();

        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO Teacher(firstname, lastname, username, userpwd) VALUES(?, ?, ?, ?);");
            statement.setString(1, fName);
            statement.setString(2, lName);
            statement.setString(3, uName);
            statement.setString(4, uPwd);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public ArrayList<TeacherEntity> readAll()
    {
        ArrayList<TeacherEntity> entities = new ArrayList<>();
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Teacher ORDER BY lastname;");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                TeacherEntity entity = new TeacherEntity();
                entity.setId_teacher(resultSet.getLong("id_teacher"));
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

    @Override
    public TeacherEntity readById(long id)
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Teacher WHERE id_teacher = ?;");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            TeacherEntity entity = new TeacherEntity();
            entity.setId_teacher(id);
            entity.setFirstName(resultSet.getString("firstname"));
            entity.setLastName(resultSet.getString("lastname"));
            entity.setUserName(resultSet.getString("username"));
            entity.setUserPwd(resultSet.getString("userpwd"));
            return entity;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TeacherEntity update(TeacherEntity obj, long id) throws OperationNotSupportedException
    {
        throw new OperationNotSupportedException("modifier un professeur : hors de la portée de ce projet");
    }

    @Override
    public void delete(TeacherEntity obj) throws OperationNotSupportedException
    {
        this.deleteById(obj.getId_teacher());
    }

    @Override
    public void deleteById(long id) throws OperationNotSupportedException
    {
        throw new OperationNotSupportedException("effacer un professeur : hors de la portée de ce projet");
    }
}
