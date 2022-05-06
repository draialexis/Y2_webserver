package com.uca.dao;

import com.uca.entity.AwardEntity;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.util.Collection;

public class AwardDAO extends _Generic<AwardEntity>
{
    @Override
    public AwardEntity create(AwardEntity obj) throws SQLException
    {
        return null;
    }

    @Override
    public Collection<AwardEntity> readAll()
    {
        return null;
    }

    @Override
    public AwardEntity readById(long id)
    {
        return null;
    }

    @Override
    public AwardEntity update(AwardEntity obj, long id) throws OperationNotSupportedException
    {
        return null;
    }

    @Override
    public void delete(AwardEntity obj) throws OperationNotSupportedException
    {

    }

    @Override
    public void deleteById(long id) throws OperationNotSupportedException
    {

    }
}
