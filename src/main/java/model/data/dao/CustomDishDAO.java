package model.data.dao;

import model.entities.CustomDish;
import model.entities.Dish;
import model.entities.Nutrients;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomDishDAO extends AbstractDAO<CustomDish> {
    @Override
    public List<CustomDish> findAll() {
        String query = "SELECT * FROM custom_dishes;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CustomDish> customDishes = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
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
                customDishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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


        return customDishes;
    }

    @Override
    public CustomDish findEntityById(int id) {
        String query = "SELECT * FROM custom_dishes WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CustomDish customDish = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return customDish;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM custom_dishes WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public synchronized boolean create(CustomDish customDish) {
        String query = "INSERT INTO custom_dishes(name, proteins, carbohydrates, fats, dish_type_id, user_id) " +
                "VALUES(?,?,?,?,?,?);";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customDish.getName());
            System.out.println(customDish.getName());
            preparedStatement.setDouble(2, customDish.getNutrients().getProteins());
            System.out.println(customDish.getNutrients().getProteins());
            preparedStatement.setDouble(3, customDish.getNutrients().getCarbohydrates());
            preparedStatement.setDouble(4, customDish.getNutrients().getFats());
            System.out.println(customDish.getDishTypeId());
            preparedStatement.setInt(5, customDish.getDishTypeId());
            System.out.println(customDish.getUserId());
            preparedStatement.setInt(6, customDish.getUserId());
            preparedStatement.execute();

            Integer id = getLastInsertedCustomDishId();
            customDish.setId(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public CustomDish update(CustomDish customDish, int key) {
        String query = "UPDATE custom_dishes SET name = ?, proteins = ?, carbohydrates = ?, fats = ?, " +
                "dish_type_id = ?, user_id = ? WHERE id = ?;";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customDish.getName());
            preparedStatement.setDouble(2, customDish.getNutrients().getProteins());
            preparedStatement.setDouble(3, customDish.getNutrients().getCarbohydrates());
            preparedStatement.setDouble(4, customDish.getNutrients().getFats());
            preparedStatement.setInt(5, customDish.getDishTypeId());
            preparedStatement.setInt(6, customDish.getUserId());
            preparedStatement.setInt(7, key);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customDish;
    }

    public List<CustomDish> getCustomDishesByUserId(int userId){
        String query = "SELECT * FROM custom_dishes WHERE user_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CustomDish> customDishes = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
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

                customDishes.add(customDish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return customDishes;
    }

    public synchronized Integer getLastInsertedCustomDishId(){
        String query = "SELECT MAX( id ) AS max_id FROM custom_dishes;";
        int id = 0;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }
}
