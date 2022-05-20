package com.uca.core;

import com.uca.dao.AwardDAO;
import com.uca.entity.AwardEntity;
import com.uca.util.IDUtil;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class AwardCore
{
    public static AwardEntity create(AwardEntity obj)
    {
        requireNonNull(obj);
        return new AwardDAO().create(obj);
    }

    public static ArrayList<AwardEntity> readAll()
    {
        return new AwardDAO().readAll();
    }

    public static ArrayList<AwardEntity> readByStudentId(long studentId)
    {
        IDUtil.requireValid(studentId);
        return new AwardDAO().readByStudentId(studentId);
    }

    public static AwardEntity readById(long id)
    {
        IDUtil.requireValid(id);
        return new AwardDAO().readById(id);
    }

    public static void deleteById(long id)
    {
        IDUtil.requireValid(id);
        new AwardDAO().deleteById(id);
    }
}
