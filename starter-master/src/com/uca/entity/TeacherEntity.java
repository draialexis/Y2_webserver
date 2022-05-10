package com.uca.entity;

public class TeacherEntity
{
    private long   id;
    private String lastName;
    private String firstName;
    private String userName;
    private String userPwd;
    private String userSalt;

    public TeacherEntity() {}

    // copy constructor (https://www.baeldung.com/java-constructors#copy)
    TeacherEntity(TeacherEntity other)
    {
        this.id = other.id;
        this.lastName = other.lastName;
        this.firstName = other.firstName;
        this.userName = other.userName;
        this.userPwd = other.userPwd;
        this.userSalt = other.userSalt;
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

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPwd()
    {
        return userPwd;
    }

    public void setUserPwd(String userPwd)
    {
        //todo add validation
        this.userPwd = userPwd;
    }

    public String getUserSalt()
    {
        return userSalt;
    }

    public void setUserSalt(String userSalt)
    {
        this.userSalt = userSalt;
    }

    @Override
    public String toString()
    {
        return "TeacherEntity{" +
               "id=" + id +
               ", lastName='" + lastName + '\'' +
               ", firstName='" + firstName + '\'' +
               ", userName='" + userName + '\'' +
               ", userPwd='" + userPwd + '\'' +
               ", userSalt='" + userSalt + '\'' +
               '}';
    }
}
