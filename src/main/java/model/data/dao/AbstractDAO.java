package model.data.dao;

import model.data.dao.connection.ConnectionManager;

import java.sql.*;
import java.util.List;

public abstract class AbstractDAO<T extends Object> {
    protected Connection connection;

    public AbstractDAO(){
        this.connection = ConnectionManager.getConnection();
    }

    public abstract List<T> findAll();
    public abstract T findEntityById(int id);
    public abstract boolean delete(int id);
    public abstract boolean create(T entity);
    public abstract T update(T entity, int key);
}
