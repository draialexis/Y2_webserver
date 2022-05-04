package com.uca.entity;

public class TeacherEntity
{
    private long   id;
    private String lastName;
    private String firstName;
    private String userName;

    private String password;
    //hash password
    private String userHPwd;
    //salt password
    private String userSPwd;

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

    public String getUserHPwd() { return userHPwd; }

    public void setUserHPwd(String userHPWD)
    {
        this.userHPwd = userHPWD;
    }

    public String getUserSPwd()
    {
        return userSPwd;
    }

    public void setUserSPwd(String userSPWD)
    {
        this.userSPwd = userSPWD;
    }
}
