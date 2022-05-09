package com.uca.dao;

import com.uca.entity.TeacherEntity;

import javax.naming.OperationNotSupportedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDAO extends _Generic<TeacherEntity>
{
    @Override
    protected TeacherEntity getFullEntity(ResultSet resultSet) throws SQLException
    {
        TeacherEntity entity = new TeacherEntity();
        entity.setId(resultSet.getLong("id_teacher"));
        entity.setFirstName(resultSet.getString("firstname"));
        entity.setLastName(resultSet.getString("lastname"));
        entity.setUserName(resultSet.getString("username"));
        entity.setUserPwd(resultSet.getString("userpwd"));
        entity.setUserSalt(resultSet.getString("usersalt"));
        return entity;
    }

    @Override
    public TeacherEntity create(TeacherEntity obj)
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO Teacher(firstname, lastname, username, userpwd, usersalt) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1, obj.getFirstName());
            statement.setString(2, obj.getLastName());
            statement.setString(3, obj.getUserName());
            statement.setString(4, obj.getUserPwd());
            statement.setString(5, obj.getUserSalt());
            statement.executeUpdate();
            return obj;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<TeacherEntity> readAll()
    {
        ArrayList<TeacherEntity> entities = new ArrayList<>();
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Teacher ORDER BY id_teacher;");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                entities.add(getFullEntity(resultSet));
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
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return getFullEntity(resultSet);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public TeacherEntity readByUserName(String userName)
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Teacher WHERE username = ?;");
            statement.setString(1, userName); // username is UNIQUE, no risk of amibuguity
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return getFullEntity(resultSet);
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
        this.deleteById(obj.getId());
    }

    @Override
    public void deleteById(long id) throws OperationNotSupportedException
    {
        throw new OperationNotSupportedException("effacer un professeur : hors de la portée de ce projet");
    }

}
