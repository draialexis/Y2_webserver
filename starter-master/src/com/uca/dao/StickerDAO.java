package com.uca.dao;

import com.uca.entity.Color;
import com.uca.entity.Description;
import com.uca.entity.StickerEntity;
import com.uca.util.IDUtil;
import com.uca.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class StickerDAO extends _Generic<StickerEntity>
{
    @Override
    StickerEntity getFullEntity(ResultSet resultSet) throws SQLException
    {
        requireNonNull(resultSet);
        StickerEntity entity = new StickerEntity();
        entity.setId(resultSet.getLong("id_sticker"));
        entity.setColor(Color.valueOf(resultSet.getString("color")));
        entity.setDescription(Description.valueOf(resultSet.getString("description")));
        return entity;
    }

    @Override
    public StickerEntity create(StickerEntity obj)
    {
        requireNonNull(obj);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "INSERT INTO Sticker(color, description) VALUES(?, ?);");
            statement.setString(1, StringUtil.requiredOfSize(requireNonNull(obj.getColor()).name()));
            statement.setString(2, StringUtil.requiredOfSize(requireNonNull(obj.getDescription()).name()));
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
                entities.add(this.getFullEntity(resultSet));
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
        IDUtil.requireValid(id);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Sticker WHERE id_sticker = ?;");
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
    public StickerEntity update(StickerEntity obj, long id)
    {
        requireNonNull(obj);
        IDUtil.requireValidAndIdentical(obj.getId(), id);
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "UPDATE Sticker SET color = ?, description = ? WHERE id_sticker = ?;");
            statement.setString(1, StringUtil.requiredOfSize(requireNonNull(obj.getColor()).name()));
            statement.setString(2, StringUtil.requiredOfSize(requireNonNull(obj.getDescription()).name()));
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
        requireNonNull(obj);
        this.deleteById(obj.getId());
    }

    @Override
    public void deleteById(long id)
    {
        IDUtil.requireValid(id);
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

    public boolean comboExists(Color color, Description description)
    {
        String colorString       = StringUtil.requiredOfSize(requireNonNull(color).name());
        String descriptionString = StringUtil.requiredOfSize(requireNonNull(description).name());
        try
        {
            PreparedStatement statement = this.connect.prepareStatement(
                    "SELECT * FROM Sticker WHERE color = ? AND description = ?;");
            statement.setString(1, colorString);
            statement.setString(2, descriptionString);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return this.getFullEntity(resultSet) != null;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
