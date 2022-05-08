package com.uca.core;

import com.uca.dao.StudentDAO;
import com.uca.entity.StudentEntity;

import java.util.ArrayList;

public class StudentCore
{
    public static StudentEntity create(StudentEntity obj)
    {
        return new StudentDAO().create(obj);
    }

    public static ArrayList<StudentEntity> readAll()
    {
        return new StudentDAO().readAll();
    }

    public static StudentEntity readById(long id)
    {
        return new StudentDAO().readById(id);
    }

    public static StudentEntity update(StudentEntity obj, long id)
    {
        return new StudentDAO().update(obj, id);
    }

    public static void deleteById(long id) {
        new StudentDAO().deleteById(id);
    }
}
