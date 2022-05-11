package com.uca.entity;

import com.uca.util.IDUtil;

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
        Objects.requireNonNull(other);
        this.id = other.id;
        this.color = other.color;
        this.description = other.description;
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = IDUtil.requireValid(id);
    }

    public Color getColor()
    {
        return this.color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Description getDescription()
    {
        return this.description;
    }

    public void setDescription(Description description)
    {
        this.description = description;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.color, this.description);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        StickerEntity that = (StickerEntity) o;
        return this.color == that.color && this.description == that.description;
    }

    @Override
    public String toString()
    {
        return "StickerEntity{" +
               "id=" + this.id +
               ", color=" + this.color +
               ", description='" + this.description + '\'' +
               '}';
    }
}
