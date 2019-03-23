package model.data.dao.dao_interfaces;

import model.entities.Entity;

import java.sql.*;
import java.util.List;

/**
 *  <h1>AbstractDAO interface</h1>
 *  AbstractDAO interface is an interface that includes all CRUD (create,
 *  read, update, delete) operations for a relative entity.
 *  The classes that implement this interface have to override all these methods to get access to
 *  the relative entity.
 *  There is a generic type that represents all entity classes in the application.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public interface AbstractDAO<T extends Entity> {
    /**
     * This method is used to find all entities from the corresponding table in the
     * database.
     * @return List of all of the entities available in the table.
     */
    List<T> findAll();

    /**
     * This method is used to find entity by its id in the database.
     * @param id This is the id of the entity is needed to find.
     * @return T (Entity) returns the entity by the given id.
     */
    T findEntityById(int id);

    /**
     * This method is used to delete entity by its id.
     * @param id This is id of the entity is needed to delete.
     * @return boolean It returns the boolean value depending on whether entity was successfully deleted.
     * (by given id)
     */
    boolean delete(int id);

    /**
     * This method is used to create custom dish by given object of the corresponding class.
     * @param entity This is the item which values will be inserted into the relative table.
     * @return boolean It returns the boolean value depending on whether entity was successfully created.
     */
    boolean create(T entity);

    /**
     * This method is used to update custom dish value in the database by its key.
     * @param entity This is the custom dish which values will be inserted into the table "custom_dishes"
     * @param key This is the id which is used to update the corresponding value
     * @return T (Entity) It returns given entity
     */
    T update(T entity, int key);
}