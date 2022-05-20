package com.uca.dao;

import com.uca.entity.StudentEntity;
import com.uca.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.uca.util.IDUtil.requireValidAndIdenticalIds;
import static com.uca.util.IDUtil.requireValidId;
import static java.util.Objects.requireNonNull;

public class StudentDAO extends _Generic<StudentEntity>
{
    @Override
    StudentEntity getFullEntity(ResultSet resultSet) throws SQLException
    {
        requireNonNull(resultSet);
        StudentEntity entity = new StudentEntity();
        entity.setId(resultSet.getLong("id_student"));
        entity.setFirstName(resultSet.getString("firstname"));
        entity.setLastName(resultSet.getString("lastname"));
        return entity;
    }

    @Override
    public StudentEntity create(StudentEntity obj)
    {
        requireNonNull(obj);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO Student(lastname, firstname) VALUES(?, ?);");
            statement.setString(1, StringUtil.requiredOfSize(obj.getLastName()));
            statement.setString(2, StringUtil.requiredOfSize(obj.getFirstName()));
            statement.executeUpdate();
            return obj;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<StudentEntity> readAll()
    {
        ArrayList<StudentEntity> entities = new ArrayList<>();
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Student ORDER BY id_student;");

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
    public StudentEntity readById(long id)
    {
        requireValidId(id);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Student WHERE id_student = ?;");
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

    @Override
    public StudentEntity update(StudentEntity obj, long id)
    {
        requireNonNull(obj);
        requireValidAndIdenticalIds(obj.getId(), id);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE Student SET lastname = ?, firstname = ? WHERE id_student = ?;");
            statement.setString(1, StringUtil.requiredOfSize(obj.getFirstName()));
            statement.setString(2, StringUtil.requiredOfSize(obj.getLastName()));
            statement.setLong(3, id);
            statement.executeUpdate();
            return obj;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(StudentEntity obj)
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
                    "DELETE FROM Student WHERE id_student = ? ;");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
