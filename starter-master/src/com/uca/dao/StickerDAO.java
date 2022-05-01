package com.uca.dao;

import com.uca.entity.Color;
import com.uca.entity.StickerEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StickerDAO extends _Generic<StickerEntity>
{

    @Override
    public StickerEntity create(StickerEntity obj)
    {
        String color       = obj.getColor().toString();
        String description = obj.getDescription();

        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO Sticker(color, description) VALUES(?, ?);");
            statement.setString(1, color);
            statement.setString(2, description);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return obj;
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
                StickerEntity entity = new StickerEntity();
                entity.setId(resultSet.getLong("id_sticker"));
                entity.setColor(Color.valueOf(resultSet.getString("color")));
                entity.setDescription(resultSet.getString("description"));

                entities.add(entity);
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
            StickerEntity entity = new StickerEntity();
            entity.setId(id);
            entity.setColor(Color.valueOf(resultSet.getString("color")));
            entity.setDescription(resultSet.getString("description"));
            return entity;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null; //TODO decide if we can let this return null... probably not
    }

    @Override
    public StickerEntity update(StickerEntity obj, long id)
    {
        String color       = obj.getColor().toString();
        String description = obj.getDescription();

        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE Sticker SET color = ?, description = ? WHERE id_sticker = ?;");
            statement.setString(1, color);
            statement.setString(2, description);
            statement.setLong(3, id);
            statement.executeUpdate();

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
