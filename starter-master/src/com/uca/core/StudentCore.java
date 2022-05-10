package com.uca.core;

import com.uca.dao.StudentDAO;
import com.uca.entity.StudentEntity;
import com.uca.util.IDUtil;

import java.util.ArrayList;
import java.util.Objects;

public class StudentCore
{
    public static StudentEntity create(StudentEntity obj)
    {
        Objects.requireNonNull(obj);
        return new StudentDAO().create(obj);
    }

    public static ArrayList<StudentEntity> readAll()
    {
        return new StudentDAO().readAll();
    }

    public static StudentEntity readById(long id)
    {
        IDUtil.requireValid(id);
        return new StudentDAO().readById(id);
    }

    public static StudentEntity update(StudentEntity obj, long id)
    {
        Objects.requireNonNull(obj);
        IDUtil.requireValid(id);
        return new StudentDAO().update(obj, id);
    }

    public static void deleteById(long id)
    {
        IDUtil.requireValid(id);
        new StudentDAO().deleteById(id);
    }
}
