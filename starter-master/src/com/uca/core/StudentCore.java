package com.uca.core;

import com.uca.dao.StudentDAO;
import com.uca.entity.StudentEntity;

public class StudentCore
{
    public static StudentEntity readById(long id)
    {
        return new StudentDAO().readById(id);
    }
}
