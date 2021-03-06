package com.uca.entity;

import static com.uca.util.IDUtil.requireValidId;
import static java.util.Objects.requireNonNull;

public class StickerEntity
{
    private long        id;
    private Color       color;
    private Description description;

    public StickerEntity() {}

    // copy constructor (https://www.baeldung.com/java-constructors#copy)
    StickerEntity(StickerEntity other)
    {
        requireNonNull(other);
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
        this.id = requireValidId(id);
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
    public String toString()
    {
        return "StickerEntity{" +
               "id=" + this.id +
               ", color=" + this.color +
               ", description='" + this.description + '\'' +
               '}';
    }
}
