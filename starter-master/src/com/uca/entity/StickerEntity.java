package com.uca.entity;

import java.util.Objects;

//final because we overrode equals() and hash()
public final class StickerEntity
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StickerEntity that = (StickerEntity) o;
        return color == that.color && description == that.description;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(color, description);
    }
}
