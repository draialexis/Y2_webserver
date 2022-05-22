package com.uca.dao;

import com.uca.entity.TeacherEntity;
import com.uca.util.PropertiesReader;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.uca.util.IDUtil.requireValidId;
import static com.uca.util.StringUtil.requiredShortString;
import static java.util.Objects.requireNonNull;

public class TeacherDAO extends _Generic<TeacherEntity>
{
    @Override
    TeacherEntity getFullEntity(ResultSet resultSet) throws SQLException
    {
        requireNonNull(resultSet);
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
        requireNonNull(obj);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO Teacher(firstname, lastname, username, userpwd, usersalt) VALUES(?, ?, ?, ?, ?);");
            statement.setString(1, requiredShortString(obj.getFirstName()));
            statement.setString(2, requiredShortString(obj.getLastName()));
            statement.setString(3, requiredShortString(obj.getUserName()));
            statement.setString(4, requiredShortString(obj.getUserPwd()));
            statement.setString(5, requiredShortString(obj.getUserSalt()));
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
        requiredShortString(userName);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Teacher WHERE username = ?;");
            statement.setString(1, userName); // username is UNIQUE, no risk of amibuguity
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return this.getFullEntity(resultSet);
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
                entities.add(this.getFullEntity(resultSet));
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
        requireValidId(id);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Teacher WHERE id_teacher = ?;");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return this.getFullEntity(resultSet);
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

    @Override
    public void delete(TeacherEntity obj)
    {
        requireNonNull(obj);
        this.deleteById(obj.getId());
    }

    @Override
    public void deleteById(long id)
    {
        requireValidId(id);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM Teacher WHERE id_teacher = ? ;");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isAdmin(long id) throws IOException
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Teacher WHERE id_teacher = ?;");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                TeacherEntity teacher = this.getFullEntity(resultSet);
                return teacher != null
                       && (
                               teacher.getUserName().equals(new PropertiesReader().getProperty("admin1"))
                               || teacher.getUserName().equals(new PropertiesReader().getProperty("admin2"))
                       );
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
