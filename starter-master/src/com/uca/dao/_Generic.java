package com.uca.dao;

import java.sql.Connection;

public abstract class _Generic<T> {

// TODO expand CRUD?

    public Connection connect = _Connector.getInstance();

    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj l'objet en question
     */
    public abstract T create(T obj);

    /**
     * Permet la suppression d'une entrée de la base
     * @param obj l'objet en question
     */
    public abstract void delete(T obj);

}
