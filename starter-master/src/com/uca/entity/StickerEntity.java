package com.uca.entity;

public class StickerEntity
{
    private long    id_sticker;
    private Color  color;
    private String description;

    public StickerEntity()
    {
        // never used
    }

    public long getId_sticker()
    {
        return id_sticker;
    }

    public void setId_sticker(long id_sticker)
    {
        this.id_sticker = id_sticker;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
