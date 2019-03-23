package model.data.dao;

import model.entities.CustomDish;
import model.entities.Dish;
import model.entities.Nutrients;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *  <h1>CustomDishDAO class</h1>
 *  CustomDishDAO represents a way to access database to the corresponding to the
 *  CustomDish entity table "custom_dish" via JDBC API by SQL server.
 *  It represents the way to access to the value needed and make basic CRUD (create,
 *  read, update, delete) operations and some more added functionality.
 *  Moreover, it gives the opportunity to initialize the entity
 *  objects(CustomDish class) on the side of model which makes it easier to manipulate with the objects
 *  in the application in the object-oriented way.
 *  It extends an abstract AbstractDAO class and therefore overrides some its methods.
 *
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */

public class CustomDishDAO extends AbstractDAO<CustomDish> {
    private static Logger log = Logger.getLogger(CustomDishDAO.class);

    /**
     * This method is used to find all custom dishes from the corresponding table in the
     * database.
     * @return List of all of the custom dishes available in the table.
     */
    @Override
    public List<CustomDish> findAll() {
        log.trace("Finding all custom dishes");
        String query = "SELECT * FROM custom_dishes;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CustomDish> customDishes = new ArrayList<>();
        try {
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
                int userId = resultSet.getInt("user_id");

                Nutrients nutrients = new Nutrients(proteins, carbohydrates, fats);
                CustomDish dish = new CustomDish();
                dish.setName(name);
                dish.setNutrients(nutrients);
                dish.setDishTypeId(dishTypeId);
                dish.setId(id);
                dish.setUserId(userId);
                log.trace("Obtained dish: " + dish);
                log.trace("Adding to the list");
                customDishes.add(dish);
            }
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } finally {
                try {
                    if(resultSet != null)
                        resultSet.close();
                    if(preparedStatement != null)
                        preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        log.trace("Returning custom dishes");
        return customDishes;
    }

    /**
     * This method is used to find custom dish by its id in the database.
     * @param id This is the id of the custom dish is needed to find.
     * @return Dish It returns the custom dish by the given id.
     */
    @Override
    public CustomDish findEntityById(int id) {
        log.trace("Finding custom dish by id = " + id);
        String query = "SELECT * FROM custom_dishes WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CustomDish customDish = null;
        try {
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
                int userId = resultSet.getInt("user_id");

                Nutrients nutrients = new Nutrients(proteins, carbohydrates, fats);
                customDish = new CustomDish();
                customDish.setName(name);
                customDish.setDishTypeId(dishTypeId);
                customDish.setNutrients(nutrients);
                customDish.setUserId(userId);
                customDish.setId(id);
                log.trace("Custom dish is found: " + customDish);
            }
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } finally {
            try {
                if(resultSet != null)
                    resultSet.close();
                if(preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.trace("Returning custom dishes");
        return customDish;
    }

    /**
     * This method is used to delete custom dish by its id.
     * @param id This is id of the custom dish is needed to delete.
     * @return boolean It returns the boolean value depending on whether custom dish was successfully deleted.
     * (by given id)
     */
    @Override
    public boolean delete(int id) {
        log.info("Deleting custom dish by id = " + id);
        String query = "DELETE FROM custom_dishes WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            log.trace("Custom dish was successfully deleted");
            return true;
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.trace("Custom dish was not successfully deleted");
        return false;
    }

    /**
     * This method is used to create custom dish by given object of the corresponding class.
     * @param customDish This is the item which values will be inserted into the table "custom_dishes".
     * @return boolean It returns the boolean value depending on whether custom dish was successfully created.
     */
    @Override
    public synchronized boolean create(CustomDish customDish) {
        log.info("Creating custom dish");
        String query = "INSERT INTO custom_dishes(name, proteins, carbohydrates, fats, dish_type_id, user_id) " +
                "VALUES(?,?,?,?,?,?);";

        PreparedStatement preparedStatement = null;

        try {
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customDish.getName());
            preparedStatement.setDouble(2, customDish.getNutrients().getProteins());
            preparedStatement.setDouble(3, customDish.getNutrients().getCarbohydrates());
            preparedStatement.setDouble(4, customDish.getNutrients().getFats());
            preparedStatement.setInt(5, customDish.getDishTypeId());
            preparedStatement.setInt(6, customDish.getUserId());
            preparedStatement.execute();

            Integer id = getLastInsertedCustomDishId();
            customDish.setId(id);
            log.trace("Custom dish was successfully created: " + customDish);
            return true;
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.trace("Custom dish was not successfully created");
        return false;
    }

    /**
     * This method is used to update custom dish value in the database by its key.
     * @param customDish This is the custom dish which values will be inserted into the table "custom_dishes"
     * @param key This is the id which is used to update the corresponding value
     * @return CustomDish It returns given dish
     */
    @Override
    public CustomDish update(CustomDish customDish, int key) {
        log.trace("Updating custom dish with id = " + key);
        String query = "UPDATE custom_dishes SET name = ?, proteins = ?, carbohydrates = ?, fats = ?, " +
                "dish_type_id = ?, user_id = ? WHERE id = ?;";

        PreparedStatement preparedStatement = null;
        try {
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customDish.getName());
            preparedStatement.setDouble(2, customDish.getNutrients().getProteins());
            preparedStatement.setDouble(3, customDish.getNutrients().getCarbohydrates());
            preparedStatement.setDouble(4, customDish.getNutrients().getFats());
            preparedStatement.setInt(5, customDish.getDishTypeId());
            preparedStatement.setInt(6, customDish.getUserId());
            preparedStatement.setInt(7, key);
            preparedStatement.execute();
            log.trace("Custom dish was successfully updated");
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.trace("Returning custom dish");
        return customDish;
    }

    /**
     * This method is used to find all custom dishes from the corresponding table in the
     * database by the given user id.
     * @param userId Id of the user
     * @return List of all of the custom dishes available by the given user id in the table.
     */
    public List<CustomDish> getCustomDishesByUserId(int userId){
        log.trace("Getting custom dishes with user id = " + userId);
        String query = "SELECT * FROM custom_dishes WHERE user_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CustomDish> customDishes = new ArrayList<>();
        try {
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            log.trace("Creating result set");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int dishTypeId = resultSet.getInt("dish_type_id");
                double proteins = resultSet.getDouble("proteins");
                double carbohydrates = resultSet.getDouble("carbohydrates");
                double fats = resultSet.getDouble("fats");

                Nutrients nutrients = new Nutrients(proteins, carbohydrates, fats);
                CustomDish customDish = new CustomDish();
                customDish.setId(id);
                customDish.setName(name);
                customDish.setDishTypeId(dishTypeId);
                customDish.setNutrients(nutrients);
                customDish.setUserId(userId);
                log.trace("Obtained custom dish: " + customDish);
                log.trace("Adding to the list");
                customDishes.add(customDish);
            }
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } finally {
            try {
                if(resultSet != null)
                    resultSet.close();
                if(preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.trace("Returning custom dishes with user id = " + userId);
        return customDishes;
    }

    /**
     * This method is used to get index of the last inserted custom dish.
     * It is mainly used while creating custom dish so that get its index for
     * future handling in the application.
     * @return Integer It returns the index of the last inserted custom dish.
     */
    public synchronized Integer getLastInsertedCustomDishId(){
        log.trace("Getting last inderted custom dish id");
        String query = "SELECT MAX( id ) AS max_id FROM custom_dishes;";
        int id = 0;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            log.trace("Craeting result set");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
        } finally{
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        log.trace("Returning last inserted custom dish id");
        return id;
    }
}
