package com.uca.dao;

import com.uca.core.StickerCore;
import com.uca.core.StudentCore;
import com.uca.core.TeacherCore;
import com.uca.entity.AwardEntity;
import com.uca.entity.StudentEntity;

import javax.naming.OperationNotSupportedException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AwardDAO extends _Generic<AwardEntity>
{
    @Override
    public AwardEntity create(AwardEntity obj) throws SQLException
    {
        long   teacherId = obj.getTeacher().getId();
        long   stickerId = obj.getSticker().getId();
        long   studentId = obj.getStudent().getId();
        Date   date      = obj.getAttributionDate();
        String motive    = obj.getMotive();

        PreparedStatement statement = this.connect.prepareStatement(
                "INSERT INTO Award(id_teacher, id_sticker, id_student, attribution_date, motive) VALUES(?, ?, ?, ?, ?);");
        statement.setLong(1, teacherId);
        statement.setLong(2, stickerId);
        statement.setLong(3, studentId);
        statement.setDate(4, date);
        statement.setString(5, motive);
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
                AwardEntity entity = new AwardEntity();
                entity.setTeacher(TeacherCore.readById(resultSet.getLong("id_teacher")));
                entity.setSticker(StickerCore.readById(resultSet.getLong("id_sticker")));
                entity.setStudent(StudentCore.readById(resultSet.getLong("id_student")));
                entity.setAttributionDate(resultSet.getDate("attribution_date"));
                entity.setMotive(resultSet.getString("motive"));
                entities.add(entity);
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

            ResultSet     resultSet = statement.executeQuery();
            StudentEntity student   = StudentCore.readById(studentId);
            while (resultSet.next())
            {
                AwardEntity entity = new AwardEntity();
                entity.setTeacher(TeacherCore.readById(resultSet.getLong("id_teacher")));
                entity.setSticker(StickerCore.readById(resultSet.getLong("id_sticker")));
                entity.setStudent(student);
                entity.setAttributionDate(resultSet.getDate("attribution_date"));
                entity.setMotive(resultSet.getString("motive"));
                entities.add(entity);
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
        return null;
    }

    @Override
    public AwardEntity update(AwardEntity obj, long id) throws OperationNotSupportedException
    {
        return null;
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
