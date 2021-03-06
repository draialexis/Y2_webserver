package com.uca.entity;

import static com.uca.util.IDUtil.requireValidId;
import static com.uca.util.StringUtil.requiredShortString;
import static java.util.Objects.requireNonNull;

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
        requireNonNull(other);
        this.id = other.id;
        this.lastName = other.lastName;
        this.firstName = other.firstName;
        this.userName = other.userName;
        this.userPwd = other.userPwd;
        this.userSalt = other.userSalt;
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

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        this.userName = requiredShortString(userName);
    }

    public String getUserPwd()
    {
        return this.userPwd;
    }

    public void setUserPwd(String userPwd)
    {
        this.userPwd = requiredShortString(userPwd);
    }

    public String getUserSalt()
    {
        return this.userSalt;
    }

    public void setUserSalt(String userSalt)
    {
        this.userSalt = requiredShortString(userSalt);
    }

    @Override
    public String toString()
    {
        return "TeacherEntity{" +
               "id=" + this.id +
               ", lastName='" + this.lastName + '\'' +
               ", firstName='" + this.firstName + '\'' +
               ", userName='" + this.userName + '\'' +
               ", userPwd='" + this.userPwd + '\'' +
               ", userSalt='" + this.userSalt + '\'' +
               '}';
    }
}
