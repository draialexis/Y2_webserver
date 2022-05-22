package com.uca.core;

import com.uca.dao.TeacherDAO;
import com.uca.entity.TeacherEntity;

import java.io.IOException;
import java.util.ArrayList;

import static com.uca.util.IDUtil.requireValidId;
import static com.uca.util.StringUtil.requiredShortString;
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
        requiredShortString(userName);
        return new TeacherDAO().readByUserName(userName);
    }

    public static TeacherEntity readById(long id)
    {
        requireValidId(id);
        return new TeacherDAO().readById(id);
    }

    public static void deleteById(long id)
    {
        requireValidId(id);
        new TeacherDAO().deleteById(id);
    }

    public static boolean isAdmin(long id) throws IOException
    {
        requireValidId(id);
        return new TeacherDAO().isAdmin(id);
    }
}