package com.uca.dao;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public abstract class _Generic<T>
{
    Connection connect = _Connector.getInstance();

    boolean areValid(long id1, long id2)
    {
        if (id1 != id2 || id1 <= 0)
        {
            throw new IllegalArgumentException(
                    String.format("IDs should be identical and strictly positive: %d; %d", id1, id2)
            );
            // just in case; this should never happen
        }
        return true;
    }

    abstract T getFullEntity(ResultSet resultSet) throws SQLException;

    public abstract T create(T obj);

    public abstract Collection<T> readAll();

    public abstract T readById(long id);

    public abstract T update(T obj, long id) throws OperationNotSupportedException;

    public abstract void delete(T obj) throws OperationNotSupportedException;

    public abstract void deleteById(long id) throws OperationNotSupportedException;
}
