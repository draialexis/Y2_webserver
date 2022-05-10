package com.uca.entity;

import com.uca.util.IDUtil;
import com.uca.util.StringUtil;

import java.util.Objects;

import static com.uca.gui.LoginHandler.PWD_SIZE_MAX;
import static com.uca.gui.LoginHandler.PWD_SIZE_MIN;

public class TeacherEntity
{
    private long   id;
    private String lastName;
    private String firstName;
    private String userName;
    private String userPwd;
    private String userSalt;

    public TeacherEntity() {}

    TeacherEntity(TeacherEntity other)
    {
        Objects.requireNonNull(other);
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
        this.id = IDUtil.requireValid(id);
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = StringUtil.required(lastName);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = StringUtil.required(firstName);
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = StringUtil.required(userName);
    }

    public String getUserPwd()
    {
        return userPwd;
    }

    public void setUserPwd(String userPwd)
    {
        if (userPwd != null && userPwd.trim().length() >= PWD_SIZE_MIN && userPwd.trim().length() <= PWD_SIZE_MAX)
        {
            this.userPwd = userPwd;
        }
        else
        {
            throw new IllegalArgumentException("password : wrong size");
        }
    }

    public String getUserSalt()
    {
        return userSalt;
    }

    public void setUserSalt(String userSalt)
    {
        this.userSalt = StringUtil.required(userSalt);
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
