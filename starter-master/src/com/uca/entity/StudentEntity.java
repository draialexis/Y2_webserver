package com.uca.entity;

import static com.uca.util.IDUtil.requireValidId;
import static com.uca.util.StringUtil.requiredShortString;
import static java.util.Objects.requireNonNull;

public class StudentEntity
{
    private long   id;
    private String lastName;
    private String firstName;

    public StudentEntity() {}

    StudentEntity(StudentEntity other)
    {
        requireNonNull(other);
        this.id = other.id;
        this.lastName = other.lastName;
        this.firstName = other.firstName;
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = requireValidId(id);
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = requiredShortString(lastName);
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = requiredShortString(firstName);
    }

    @Override
    public String toString()
    {
        return "StudentEntity{" +
               "id=" + this.id +
               ", lastName='" + this.lastName + '\'' +
               ", firstName='" + this.firstName + '\'' +
               '}';
    }
}
