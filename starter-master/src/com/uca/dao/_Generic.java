package com.uca.dao;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.util.Collection;

public abstract class _Generic<T>
{
    public Connection connect = _Connector.getInstance();

    public abstract T create(T obj);

    public abstract Collection<T> readAll();

    public abstract T readById(long id);

    public abstract T update(T obj, long id) throws OperationNotSupportedException;

    public abstract void delete(T obj) throws OperationNotSupportedException;

    public abstract void deleteById(long id) throws OperationNotSupportedException;
}
