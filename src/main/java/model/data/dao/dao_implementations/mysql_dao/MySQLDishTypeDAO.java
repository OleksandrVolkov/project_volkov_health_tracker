package model.data.dao.dao_implementations.mysql_dao;

import model.data.dao.connection.ConnectionPool;
import model.data.dao.dao_interfaces.DishTypeDAO;
import model.entities.DishType;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>MySQLDishTypeDAO class</h1>
 *  MySQLDishTypeDAO represents a way to access database to the corresponding to the
 *  DishType entity table "dish_types" via JDBC API by SQL server using MySQL implementation.
 *  It represents the way to access to the value needed and make basic CRUD (create,
 *  read, update, delete) operations and some more added functionality.
 *  Moreover, it gives the opportunity to initialize the entity
 *  objects(DishType class) on the side of model which makes it easier to manipulate with the objects
 *  in the application in the object-oriented way.
 *  It implements a DishTypeDAO interface and therefore overrides some its methods.
 *
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class MySQLDishTypeDAO implements DishTypeDAO {
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(MySQLDishTypeDAO.class);
    /**
     * ConnectionPool to handle multiple connections from various threads
     */
    private ConnectionPool connectionPool;
    /**
     * Constructor of the class to instantiate connectionPool field
     */
    public MySQLDishTypeDAO(){
        connectionPool = new ConnectionPool();
    }

    /**
     * This method is used to find all dish types from the corresponding table in the
     * database.
     * @return List of all of the dish types available in the table.
     */
    @Override
    public List<DishType> findAll() {
        log.trace("Finding all dish types");
        List<DishType> dishTypes = new ArrayList<>();
        String query = "SELECT * FROM dish_types";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            log.trace("Creating result set");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                DishType dishType = new DishType(name);
                dishType.setId(id);
                log.trace("Obtained dish type: " + dishType);
                log.trace("Adding to the list");
                dishTypes.add(dishType);
            }
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null)
                    resultSet.close();
                if(preparedStatement != null)
                    preparedStatement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.trace("Returning dish types");
        return dishTypes;
    }

    /**
     * This method is used to find dish type by its id in the database.
     * @param id This is the id of the dish type is needed to find.
     * @return DishType It returns the dish type by the given id.
     */
    @Override
    public DishType findEntityById(int id) {
        log.trace("Finding dish type by id = " + id);
        String query = "SELECT * FROM dish_types WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DishType dishType = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                dishType = new DishType(name);
                dishType.setId(id);
                log.trace("Found dish type: " + dishType);
            }
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null)
                    resultSet.close();
                if(preparedStatement != null)
                    preparedStatement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        log.trace("Returning dish type");
        return dishType;
    }

    /**
     * This method is used to delete dish type by its id.
     * @param id This is id of the dish type is needed to delete.
     * @return boolean It returns the boolean value depending on whether dish type was successfully deleted.
     * (by given id)
     */
    @Override
    public boolean delete(int id) {
        log.trace("Deleting a dish type by id = " + id);
        String query = "DELETE FROM dish_types WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            log.trace("Dish type was successfully deleted");
            return true;
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null)
                    resultSet.close();
                if(preparedStatement != null)
                    preparedStatement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.trace("Dish type was not deleted");
        return false;
    }

    /**
     * This method is used to create dish type by given object of the corresponding class.
     * @param dishType This is the item which values will be inserted into the table "dish_types".
     * @return boolean It returns the boolean value depending on whether dish type was successfully created.
     */
    @Override
    public boolean create(DishType dishType) {
        log.trace("Creating dish type");
        String query = "INSERT INTO dish_types(name) VALUES(?);";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dishType.getName());
            preparedStatement.execute();

            Integer id = getLastInsertedDishTypeId();
            dishType.setId(id);
            log.trace("Dish type is successfully created: " + dishType);
            return true;
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.trace("Dish type was't created");
        return false;
    }

    /**
     * This method is used to update dish type value in the database by its key.
     * @param dishType This is the dishType which values will be inserted into the table "dish_types"
     * @param key This is the id which is used to update the corresponding value
     * @return DishType It returns given user
     */
    @Override
    public DishType update(DishType dishType, int key) {
        log.trace("Updating dish type with id = " + key);
        String query = "UPDATE dish_types SET name = ? WHERE id = ?;";

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dishType.getName());
            preparedStatement.setInt(2, key);

            preparedStatement.execute();
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.trace("Dish type id updated");
        return dishType;
    }


    /**
     * This method is used to get index of the last inserted dish type.
     * It is mainly used while creating dish type so that get its index for
     * future handling in the application.
     * @return Integer It returns the index of the last inserted dish type.
     */
    public synchronized Integer getLastInsertedDishTypeId(){
        log.trace("Getting last inserted dish type id");
        String query = "SELECT MAX( id ) AS max_id FROM dish_types;";
        int id = 0;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            log.trace("Creating result set");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("max_id");
            }
            log.trace("DishType id [" + id + "] is returned");
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
                if(resultSet != null)
                    resultSet.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.trace("Returning last inserted dish type id");
        return id;
    }
}
