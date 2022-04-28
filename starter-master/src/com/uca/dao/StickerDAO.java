package com.uca.dao;

import com.uca.entity.Color;
import com.uca.entity.StickerEntity;
import com.uca.entity.TeacherEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StickerDAO extends _Generic<StickerEntity>
{
    public ArrayList<StickerEntity> getAllStickers()
    {
        ArrayList<StickerEntity> entities = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = this.connect.prepareStatement(
                    "SELECT * FROM Sticker ORDER BY id_sticker;");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                StickerEntity entity = new StickerEntity();
                entity.setId_sticker(resultSet.getLong("id_sticker"));
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

    public StickerEntity getById(long id)
    {
        //TODO
        return null;
    }

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

    public StickerEntity update(StickerEntity obj)
    {
        //TODO
        return null;
    }

    @Override
    public void delete(StickerEntity obj)
    {
        //TODO
    }
}
