package com.uca.dao;

import com.uca.entity.Identifiable;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public abstract class _Generic<T extends Identifiable>
{
    public Connection connect = _Connector.getInstance();

    protected abstract T getFullEntity(ResultSet resultSet) throws SQLException;

    protected void ensureIdIsSet(T obj, long id)
    {
        if (obj.getId() != id)
        {
            obj.setId(id);
        }
    }

    public abstract T create(T obj) throws SQLException;

    public abstract Collection<T> readAll();

    public abstract T readById(long id);

    public abstract T update(T obj, long id) throws OperationNotSupportedException;

    public abstract void delete(T obj) throws OperationNotSupportedException;

    public abstract void deleteById(long id) throws OperationNotSupportedException;
}
