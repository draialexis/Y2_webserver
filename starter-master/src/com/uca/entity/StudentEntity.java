package com.uca.entity;

public class StudentEntity
{
    private long   id;
    private String lastName;
    private String firstName;

    public StudentEntity() {}

    // copy constructor (https://www.baeldung.com/java-constructors#copy)
    StudentEntity(StudentEntity other)
    {
        this.id = other.id;
        this.lastName = other.lastName;
        this.firstName = other.firstName;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
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
