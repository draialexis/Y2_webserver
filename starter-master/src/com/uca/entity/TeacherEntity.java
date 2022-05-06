package com.uca.entity;

public class TeacherEntity implements Identifiable
{
    private long   id;
    private String lastName;
    private String firstName;
    private String userName;
    private String userPwd;
    private String userSalt;

    public TeacherEntity()
    {
        // never used
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
        this.userPwd = userPwd;
    }

    public String getUserSalt()
    {
        return userSalt;
    }

    public void setUserSalt(String userSPWD)
    {
        this.userSalt = userSPWD;
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
