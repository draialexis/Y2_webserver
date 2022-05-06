package com.uca.dao;

import com.uca.core.StickerCore;
import com.uca.core.StudentCore;
import com.uca.core.TeacherCore;
import com.uca.entity.AwardEntity;

import javax.naming.OperationNotSupportedException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AwardDAO extends _Generic<AwardEntity>
{

    private AwardEntity getFullEntity(ResultSet resultSet) throws SQLException
    {
        AwardEntity entity = new AwardEntity();
        entity.setId(resultSet.getLong("id_award"));
        entity.setTeacher(TeacherCore.readById(resultSet.getLong("id_teacher")));
        entity.setSticker(StickerCore.readById(resultSet.getLong("id_sticker")));
        entity.setStudent(StudentCore.readById(resultSet.getLong("id_student")));
        entity.setAttributionDate(resultSet.getDate("attribution_date"));
        entity.setMotive(resultSet.getString("motive"));
        return entity;
    }

    @Override
    public AwardEntity create(AwardEntity obj) throws SQLException
    {
        PreparedStatement statement = this.connect.prepareStatement(
                "INSERT INTO Award(attribution_date, motive, id_teacher, id_sticker, id_student) VALUES(?, ?, ?, ?, ?);");
        statement.setDate(1, obj.getAttributionDate());
        statement.setString(2, obj.getMotive());
        statement.setLong(3, obj.getTeacher().getId());
        statement.setLong(4, obj.getSticker().getId());
        statement.setLong(5, obj.getStudent().getId());
        statement.executeUpdate();
        return obj;
    }

    @Override
    public Collection<AwardEntity> readAll()
    {
        ArrayList<AwardEntity> entities = new ArrayList<>();
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Award ORDER BY id_student, attribution_date;");

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

    public Collection<AwardEntity> readByStudentId(long studentId)
    {
        ArrayList<AwardEntity> entities = new ArrayList<>();
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Award WHERE id_student = ? ORDER BY attribution_date;");
            statement.setLong(1, studentId);

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
    public AwardEntity readById(long id)
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Award WHERE id_award = ?;");
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

    @Override
    public AwardEntity update(AwardEntity obj, long id) throws OperationNotSupportedException
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE Award " +
                    "SET attribution_date = ?, motive = ?, id_teacher = ?, id_sticker = ?, id_student = ? " +
                    "WHERE id_award = ?) " +
                    "VALUES(?, ?, ?, ?, ?);");
            statement.setDate(1, obj.getAttributionDate());
            statement.setString(2, obj.getMotive());
            statement.setLong(3, obj.getTeacher().getId());
            statement.setLong(4, obj.getSticker().getId());
            statement.setLong(5, obj.getStudent().getId());
            statement.setLong(6, id);
            if (obj.getId() != id) // unnecessary?
            {
                obj.setId(id);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(AwardEntity obj) throws OperationNotSupportedException
    {

    }

    @Override
    public void deleteById(long id) throws OperationNotSupportedException
    {

    }
}
