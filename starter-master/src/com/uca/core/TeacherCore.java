package com.uca.core;

import com.uca.dao.TeacherDAO;
import com.uca.entity.TeacherEntity;

import java.util.ArrayList;

public class TeacherCore
{

    public static ArrayList<TeacherEntity> getAllTeachers() {
        return new TeacherDAO().getAllTeachers();
    }

}
