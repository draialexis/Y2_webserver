package com.uca.core;

import com.uca.dao.AwardDAO;
import com.uca.entity.AwardEntity;

import java.util.ArrayList;

import static com.uca.util.IDUtil.requireValidId;
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
        requireValidId(studentId);
        return new AwardDAO().readByStudentId(studentId);
    }

    public static AwardEntity readById(long id)
    {
        requireValidId(id);
        return new AwardDAO().readById(id);
    }

    public static void deleteById(long id)
    {
        requireValidId(id);
        new AwardDAO().deleteById(id);
    }
}
