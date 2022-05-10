package com.uca.dao;

import com.uca.entity.TeacherEntity;
import com.uca.util.IDUtil;
import com.uca.util.StringUtil;

import javax.naming.OperationNotSupportedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class TeacherDAO extends _Generic<TeacherEntity>
{
    @Override
    TeacherEntity getFullEntity(ResultSet resultSet) throws SQLException
    {
        Objects.requireNonNull(resultSet);
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
            statement.setString(1, StringUtil.requiredOfSize(obj.getFirstName()));
            statement.setString(2, StringUtil.requiredOfSize(obj.getLastName()));
            statement.setString(3, StringUtil.requiredOfSize(obj.getUserName()));
            statement.setString(4, StringUtil.requiredOfSize(obj.getUserPwd()));
            statement.setString(5, StringUtil.requiredOfSize(obj.getUserSalt()));
            statement.executeUpdate();
            return obj;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public TeacherEntity readByUserName(String userName)
    {
        StringUtil.requiredOfSize(userName);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Teacher WHERE username = ?;");
            statement.setString(1, userName); // username is UNIQUE, no risk of amibuguity
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return getFullEntity(resultSet);
            }
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
        IDUtil.requireValid(id);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Teacher WHERE id_teacher = ?;");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return getFullEntity(resultSet);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * NOT SUPPORTED
     */
    @Override
    public TeacherEntity update(TeacherEntity obj, long id) throws OperationNotSupportedException
    {
        throw new OperationNotSupportedException("update teacher: not in this project's scope");
    }

    /**
     * NOT SUPPORTED
     */
    @Override
    public void delete(TeacherEntity obj) throws OperationNotSupportedException
    {
        this.deleteById(obj.getId());
    }

    /**
     * NOT SUPPORTED
     */
    @Override
    public void deleteById(long id) throws OperationNotSupportedException
    {
        throw new OperationNotSupportedException("delete teacher: not in this project's scope");
    }

}
