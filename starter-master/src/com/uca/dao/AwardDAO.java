package com.uca.dao;

import com.uca.core.StickerCore;
import com.uca.core.StudentCore;
import com.uca.core.TeacherCore;
import com.uca.entity.AwardEntity;

import javax.naming.OperationNotSupportedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import static com.uca.util.IDUtil.requireValidId;
import static com.uca.util.StringUtil.requiredString;
import static java.util.Objects.requireNonNull;

public class AwardDAO extends _Generic<AwardEntity>
{
    @Override
    AwardEntity getFullEntity(ResultSet resultSet) throws SQLException
    {
        requireNonNull(resultSet);
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
    public AwardEntity create(AwardEntity obj)
    {
        requireNonNull(obj);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO Award(attribution_date, motive, id_sticker, id_student)" +
                    "VALUES(?, ?, ?, ?);");

            if (Objects.nonNull(obj.getTeacher()))
            {
                statement = this.connect.prepareStatement(
                        "INSERT INTO Award(attribution_date, motive, id_teacher, id_sticker, id_student)" +
                        "VALUES(?, ?, ?, ?, ?);");
                statement.setLong(3, requireValidId(obj.getTeacher().getId()));
            }
            statement.setDate(1, requireNonNull(obj.getAttributionDate()));
            statement.setString(2, requiredString(obj.getMotive()));
            statement.setLong(4, requireValidId(requireNonNull(obj.getSticker()).getId()));
            statement.setLong(5, requireValidId(requireNonNull(obj.getStudent()).getId()));
            statement.executeUpdate();
            return obj;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<AwardEntity> readAll()
    {
        ArrayList<AwardEntity> entities = new ArrayList<>();
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Award ORDER BY attribution_date DESC, id_award DESC;");

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

    public ArrayList<AwardEntity> readByStudentId(long studentId)
    {
        requireValidId(studentId);
        ArrayList<AwardEntity> entities = new ArrayList<>();
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Award WHERE id_student = ? ORDER BY attribution_date DESC, id_award DESC;");
            statement.setLong(1, studentId);

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
    public AwardEntity readById(long id)
    {
        requireValidId(id);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM Award WHERE id_award = ?;");
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
    public AwardEntity update(AwardEntity obj, long id) throws OperationNotSupportedException
    {
        throw new OperationNotSupportedException("update award: not in this project's scope");
    }

    @Override
    public void delete(AwardEntity obj)
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
                    "DELETE FROM Award WHERE id_award = ? ;");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
