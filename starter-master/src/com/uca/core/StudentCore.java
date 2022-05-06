package com.uca.core;

import com.uca.dao.StudentDAO;
import com.uca.dao.TeacherDAO;
import com.uca.entity.StudentEntity;
import com.uca.entity.TeacherEntity;

import java.util.ArrayList;

public class StudentCore
{
    public static ArrayList<StudentEntity> readAll() {return new StudentDAO().readAll();}
    public static StudentEntity readById(long id)
    {
        return new StudentDAO().readById(id);
    }
}
