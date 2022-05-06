package com.uca.entity;

public class StudentEntity
{
    private long   id;
    private String lastName;
    private String firstName;

    public StudentEntity()
    {
        // never used
    }

    long getId()
    {
        return id;
    }

    void setId(long id)
    {
        this.id = id;
    }

    String getLastName()
    {
        return lastName;
    }

    void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    String getFirstName()
    {
        return firstName;
    }

    void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    @Override
    public String toString()
    {
        return "StudentEntity{" +
               "id=" + id +
               ", lastName='" + lastName + '\'' +
               ", firstName='" + firstName + '\'' +
               '}';
    }
}
