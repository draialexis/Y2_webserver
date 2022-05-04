package com.uca.dao;

import com.uca.entity.StickerEntity;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.util.Collection;

public class PupilDAO extends _Generic<StickerEntity>
{
    @Override
    public StickerEntity create(StickerEntity obj) throws SQLException
    {
        return null;
    }

    @Override
    public Collection<StickerEntity> readAll()
    {
        return null;
    }

    @Override
    public StickerEntity readById(long id)
    {
        return null;
    }

    @Override
    public StickerEntity update(StickerEntity obj, long id) throws OperationNotSupportedException
    {
        return null;
    }

    @Override
    public void delete(StickerEntity obj) throws OperationNotSupportedException
    {

    }

    @Override
    public void deleteById(long id) throws OperationNotSupportedException
    {

    }
}
