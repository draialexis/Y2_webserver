package com.uca.entity;

public class TeacherEntity
{
    private int    id_teacher;
    private String lastName;
    private String firstName;
    private String userName;
    private String userPwd;

    public TeacherEntity()
    {
        //Ignored !
    }

    public int getId_teacher()
    {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher)
    {
        this.id_teacher = id_teacher;
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
}
