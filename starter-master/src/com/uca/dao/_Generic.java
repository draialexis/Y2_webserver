package com.uca.dao;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public abstract class _Generic<T>
{
    final Connection connect = _Connector.getInstance();

    abstract T getFullEntity(ResultSet resultSet) throws SQLException;

    public abstract T create(T obj);

    public abstract Collection<T> readAll();

    public abstract T readById(long id);

    public abstract T update(T obj, long id) throws OperationNotSupportedException;

    public abstract void delete(T obj) throws OperationNotSupportedException;

    public abstract void deleteById(long id) throws OperationNotSupportedException;
}
