package com.uca.dao;

import com.uca.entity.StudentEntity;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.util.Collection;

public class StudentDAO extends _Generic<StudentEntity>
{
    @Override
    public StudentEntity create(StudentEntity obj) throws SQLException
    {
        return null;
    }

    @Override
    public Collection<StudentEntity> readAll()
    {
        return null;
    }

    @Override
    public StudentEntity readById(long id)
    {
        return null;
    }

    @Override
    public StudentEntity update(StudentEntity obj, long id) throws OperationNotSupportedException
    {
        return null;
    }

    @Override
    public void delete(StudentEntity obj) throws OperationNotSupportedException
    {

    }

    @Override
    public void deleteById(long id) throws OperationNotSupportedException
    {

    }
}
