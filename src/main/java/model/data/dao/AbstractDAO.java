package model.data.dao;

import model.data.dao.connection.ConnectionManager;
import model.entities.Entity;

import java.sql.*;
import java.util.List;

/**
 *  <h1>AbstractDAO class</h1>
 *  AbstractDAO class is an abstract class that includes all CRUD (create,
 *  read, update, delete) operations for a relative entity.
 *  The derived classes have to override all these methods to get access to
 *  the relative entity.
 *  There is a generic type that respresents all entity classes in the
 *  application.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public abstract class AbstractDAO<T extends Entity> {
    /**
     * A connection that is used to get access to the server
     * and initialise such JDBC interfaces as ResultSet, PreparedStatement
     * and so on
     */
    protected Connection connection;


    /**
     * A constructor to initialise connection with the static method getConnection
     * from ConnectionManager class
     */
    public AbstractDAO(){
        this.connection = ConnectionManager.getConnection();
    }

    /**
     * This method is used to find all entities from the corresponding table in the
     * database.
     * @return List of all of the entities available in the table.
     */
    public abstract List<T> findAll();

    /**
     * This method is used to find entity by its id in the database.
     * @param id This is the id of the entity is needed to find.
     * @return T (Entity) returns the entity by the given id.
     */
    public abstract T findEntityById(int id);

    /**
     * This method is used to delete entity by its id.
     * @param id This is id of the entity is needed to delete.
     * @return boolean It returns the boolean value depending on whether entity was successfully deleted.
     * (by given id)
     */
    public abstract boolean delete(int id);

    /**
     * This method is used to create custom dish by given object of the corresponding class.
     * @param entity This is the item which values will be inserted into the relative table.
     * @return boolean It returns the boolean value depending on whether entity was successfully created.
     */
    public abstract boolean create(T entity);

    /**
     * This method is used to update custom dish value in the database by its key.
     * @param entity This is the custom dish which values will be inserted into the table "custom_dishes"
     * @param key This is the id which is used to update the corresponding value
     * @return T (Entity) It returns given entity
     */
    public abstract T update(T entity, int key);
}