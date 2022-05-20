package com.uca.core;

import com.uca.dao.TeacherDAO;
import com.uca.entity.TeacherEntity;
import com.uca.util.StringUtil;

import java.util.ArrayList;

import static com.uca.util.IDUtil.requireValidId;
import static java.util.Objects.requireNonNull;

public class TeacherCore
{
    public static TeacherEntity create(TeacherEntity obj)
    {
        requireNonNull(obj);
        return new TeacherDAO().create(obj);
    }

    public static ArrayList<TeacherEntity> readAll()
    {
        return new TeacherDAO().readAll();
    }

    public static TeacherEntity readByUserName(String userName)
    {
        StringUtil.requiredOfSize(userName);
        return new TeacherDAO().readByUserName(userName);
    }

    public static TeacherEntity readById(long id)
    {
        requireValidId(id);
        return new TeacherDAO().readById(id);
    }
}