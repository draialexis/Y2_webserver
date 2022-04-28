package com.uca.entity;

public class StickerEntity
{
    private int    id_sticker;
    private Color  color;
    private String description;

    public StickerEntity()
    {
        // never used
    }

    int getId_sticker()
    {
        return id_sticker;
    }

    void setId_sticker(int id_sticker)
    {
        this.id_sticker = id_sticker;
    }

    Color getColor()
    {
        return color;
    }

    void setColor(Color color)
    {
        this.color = color;
    }

    String getDescription()
    {
        return description;
    }

    void setDescription(String description)
    {
        this.description = description;
    }
}
