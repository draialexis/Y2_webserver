package com.uca.dao;

import com.uca.entity.Color;
import com.uca.entity.Description;
import com.uca.entity.StickerEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StickerDAO extends _Generic<StickerEntity>
{
    @Override
    StickerEntity getFullEntity(ResultSet resultSet) throws SQLException
    {
        StickerEntity entity = new StickerEntity();
        entity.setId(resultSet.getLong("id_sticker"));
        entity.setColor(Color.valueOf(resultSet.getString("color")));
        entity.setDescription(Description.valueOf(resultSet.getString("description")));
        return entity;
    }

    @Override
    public StickerEntity create(StickerEntity obj)
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO Sticker(color, description) VALUES(?, ?);");
            statement.setString(1, obj.getColor().toString());
            statement.setString(2, obj.getDescription().toString());
            statement.executeUpdate();
            return obj;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<StickerEntity> readAll()
    {
        ArrayList<StickerEntity> entities = new ArrayList<>();
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Sticker ORDER BY id_sticker;");

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
    public StickerEntity readById(long id)
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Sticker WHERE id_sticker = ?;");
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
    public StickerEntity update(StickerEntity obj, long id)
    {
        if (obj.getId() != id)
        {
            throw new IllegalArgumentException(String.format("IDs do not conform: %d != %d", obj.getId(), id));
            // just in case; this should never happen
        }
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE Sticker SET color = ?, description = ? WHERE id_sticker = ?;");
            statement.setString(1, obj.getColor().toString());
            statement.setString(2, obj.getDescription().toString());
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
    public void delete(StickerEntity obj)
    {
        this.deleteById(obj.getId());
    }

    @Override
    public void deleteById(long id)
    {
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "DELETE FROM Sticker WHERE id_sticker = ? ;");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
