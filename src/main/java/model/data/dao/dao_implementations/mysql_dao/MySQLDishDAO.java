package model.data.dao.dao_implementations.mysql_dao;

import model.data.dao.connection.ConnectionPool;
import model.data.dao.dao_interfaces.DishDAO;
import model.entities.Dish;
import model.entities.Nutrients;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *  <h1>MySQLDishDAO class</h1>
 *  MySQLDishDAO represents a way to access database to the corresponding to the
 *  Dish entity table "dishes" via JDBC API by SQL server using MySQL implementation.
 *  It represents the way to access to the value needed and make basic CRUD (create,
 *  read, update, delete) operations and some more added functionality.
 *  Moreover, it gives the opportunity to initialize the entity
 *  objects(Dish class) on the side of model which makes it easier to manipulate with the objects
 *  in the application in the object-oriented way.
 *  It implements a DishDAO interface and therefore overrides some its methods.
 *
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */

public class MySQLDishDAO implements DishDAO {
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(MySQLDishDAO.class);
    /**
     * ConnectionPool to handle multiple connections from various threads
     */
    private ConnectionPool connectionPool;
    /**
     * Constructor of the class to instantiate connectionPool field
     */
    public MySQLDishDAO(){
        connectionPool = new ConnectionPool();
    }
    /**
     * This method is used to find all dishes from the corresponding table in the
     * database.
     * @return List of all of the dishes available in the table.
     */
    @Override
    public List<Dish> findAll() {
        log.info("Finding all dishes");
        String query = "SELECT * FROM dishes;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dish> dishes = new ArrayList<>();
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
                int dishTypeId = resultSet.getInt("dish_type_id");
                double proteins = resultSet.getDouble("proteins");
                double carbohydrates = resultSet.getDouble("carbohydrates");
                double fats = resultSet.getDouble("fats");


                Nutrients nutrients = new Nutrients(proteins, carbohydrates, fats);
                Dish dish = new Dish();
                dish.setName(name);
                dish.setNutrients(nutrients);
                dish.setDishTypeId(dishTypeId);
                dish.setId(id);
                log.trace("Obtained dish: " + dish);
                log.trace("Adding to the list");
                dishes.add(dish);
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

        log.trace("Returning all dishes");
        return dishes;
    }

    /**
     * This method is used to find dish by its id in the database.
     * @param id This is the id of the dish is needed to find.
     * @return Dish It returns the dish by the given id.
     */
    @Override
    public Dish findEntityById(int id) {
        log.info("Finding dish by id = " + id);
        String query = "SELECT * FROM dishes WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Dish dish = null;
        Connection connection = null;

        try {
            DataSource dataSource = connectionPool.setUpPool();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            log.trace("Creating result set");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                int dishTypeId = resultSet.getInt("dish_type_id");
                double proteins = resultSet.getDouble("proteins");
                double carbohydrates = resultSet.getDouble("carbohydrates");
                double fats = resultSet.getDouble("fats");

                Nutrients nutrients = new Nutrients(proteins, carbohydrates, fats);
                dish = new Dish();
                dish.setName(name);
                dish.setDishTypeId(dishTypeId);
                dish.setNutrients(nutrients);
                dish.setId(id);
                log.trace("Found dish: " + dish);
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
        return dish;
    }

    /**
     * This method is used to delete dish by its id.
     * @param id This is id of the dish is needed to delete.
     * @return boolean It returns the boolean value depending on whether dish was successfully deleted.
     * (by given id)
     */
    @Override
    public boolean delete(int id) {
        log.trace("Deleting dish by id = " + id);
        String query = "DELETE FROM dishes WHERE id = ?";
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
            log.trace("Dish was successfully deleted");
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
        log.trace("Dish was not successfully deleted");
        return false;
    }

    /**
     * This method is used to create dish by given object of the corresponding class.
     * @param dish This is the item which values will be inserted into the table "dishes".
     * @return boolean It returns the boolean value depending on whether dish was successfully created.
     */
    @Override
    public boolean create(Dish dish) {
        log.trace("Craeting dish");
        String query = "INSERT INTO dishes(name, proteins, carbohydrates, fats, dish_type_id) " +
                "VALUES(?,?,?,?,?);";

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dish.getName());
            preparedStatement.setDouble(2, dish.getNutrients().getProteins());
            preparedStatement.setDouble(3, dish.getNutrients().getCarbohydrates());
            preparedStatement.setDouble(4, dish.getNutrients().getFats());
            preparedStatement.setInt(5, dish.getDishTypeId());
            preparedStatement.execute();


            dish.setId(getLastInsertedId());
            log.trace("Dish was successfully created: " + dish);
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
        log.trace("Dish was not successfully created");
        return false;
    }

    /**
     * This method is used to update dish value in the database by its key.
     * @param dish This is the dish which values will be inserted into the table "dishes"
     * @param key This is the id which is used to update the corresponding value
     * @return Dish It returns given dish
     */
    @Override
    public Dish update(Dish dish, int key) {
        log.trace("Updating dish with id = " + key);
        String query = "UPDATE dishes SET name = ?, proteins = ?, carbohydrates = ?, fats = ?, " +
                "dish_type_id = ? WHERE id = ?;";

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dish.getName());
            preparedStatement.setDouble(2, dish.getNutrients().getProteins());
            preparedStatement.setDouble(3, dish.getNutrients().getCarbohydrates());
            preparedStatement.setDouble(4, dish.getNutrients().getFats());
            preparedStatement.setInt(5, dish.getDishTypeId());
            preparedStatement.setInt(6, key);
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
        log.trace("Returning obtained dishes");
        return dish;
    }

    /**
     * This method is used to get index of the last inserted dish.
     * It is mainly used while creating dish so that get its index for
     * future handling in the application.
     * @return Integer It returns the index of the last inserted dish.
     */
    //TODO:: INTEGER VS int!!!!?!?!?!? as the returned value
    public Integer getLastInsertedId(){
        log.trace("Getting last inserted dish id");
        String query = "SELECT MAX(ID) AS max_id FROM dishes;";
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
            if (resultSet.next()) {
                id = resultSet.getInt("max_id");
            }
            log.trace("Dish id [" + id + "] is returned");
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        log.trace("Returning last inserted dish id");
        return id;
    }
}
