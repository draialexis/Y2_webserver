package com.uca.core;

import com.uca.dao.StudentDAO;
import com.uca.dao.TeacherDAO;
import com.uca.entity.StudentEntity;
import com.uca.entity.TeacherEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentCore
{
    public static long findLastId() throws SQLException {return new StudentDAO().findLastId();}
    public static StudentEntity create(StudentEntity obj) {return  new StudentDAO().create(obj);}
    public static ArrayList<StudentEntity> readAll() {return new StudentDAO().readAll();}
    public static StudentEntity readById(long id)
    {
        return new StudentDAO().readById(id);
    }
    public static StudentEntity update(StudentEntity obj, long id) {return new StudentDAO().update(obj, id);}
    public static void deletbyId(long id_stud) {new StudentDAO().deleteById(id_stud);}
}
