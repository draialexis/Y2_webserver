package com.uca.core;

import com.uca.dao.TeacherDAO;
import com.uca.entity.TeacherEntity;
import com.uca.util.IDUtil;
import com.uca.util.StringUtil;

import java.util.ArrayList;
import java.util.Objects;

public class TeacherCore
{
    public static TeacherEntity create(TeacherEntity obj)
    {
        Objects.requireNonNull(obj);
        return new TeacherDAO().create(obj);
    }

    public static ArrayList<TeacherEntity> readAll()
    {
        return new TeacherDAO().readAll();
    }

    public static TeacherEntity readByUserName(String userName)
    {
        StringUtil.required(userName);
        return new TeacherDAO().readByUserName(userName);
    }

    public static TeacherEntity readById(long id)
    {
        IDUtil.requireValid(id);
        return new TeacherDAO().readById(id);
    }
}