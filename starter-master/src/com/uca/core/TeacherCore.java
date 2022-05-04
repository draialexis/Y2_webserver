package com.uca.core;

import com.uca.dao.TeacherDAO;
import com.uca.entity.TeacherEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherCore
{
    public static ArrayList<TeacherEntity> readAll()
    {
        return new TeacherDAO().readAll();
    }

    public static TeacherEntity readByUserName(String userName)
    {
        return new TeacherDAO().readByUserName(userName);
    }

    public static TeacherEntity readById(long id)
    {
        return new TeacherDAO().readById(id);
    }

    public static TeacherEntity create(TeacherEntity obj) throws SQLException
    {
        return new TeacherDAO().create(obj);
    }
}
