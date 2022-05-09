package com.uca.entity;

public class StickerEntity
{
    private long        id;
    private Color       color;
    private Description description;

    public StickerEntity() {}

    // copy constructor (https://www.baeldung.com/java-constructors#copy)
    StickerEntity(StickerEntity other)
    {
        this.id = other.id;
        this.color = other.color;
        this.description = other.description;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Description getDescription()
    {
        return description;
    }

    public void setDescription(Description description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "StickerEntity{" +
               "id=" + id +
               ", color=" + color +
               ", description='" + description + '\'' +
               '}';
    }
}
