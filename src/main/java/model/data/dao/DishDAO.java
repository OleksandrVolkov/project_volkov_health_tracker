package model.data.dao;

import model.entities.Dish;
import model.entities.Nutrients;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDAO extends AbstractDAO<Dish> {
    @Override
    public List<Dish> findAll() {
        String query = "SELECT * FROM dishes;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dish> dishes = new ArrayList<>();
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


                Nutrients nutrients = new Nutrients(proteins, carbohydrates, fats);
                Dish dish = new Dish();
                dish.setName(name);
                dish.setNutrients(nutrients);
                dish.setDishTypeId(dishTypeId);
                dish.setId(id);
                dishes.add(dish);
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

        return dishes;
    }

    @Override
    public Dish findEntityById(int id) {
        String query = "SELECT * FROM dishes WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Dish dish = null;
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

                Nutrients nutrients = new Nutrients(proteins, carbohydrates, fats);
                dish = new Dish();
                dish.setName(name);
                dish.setDishTypeId(dishTypeId);
                dish.setNutrients(nutrients);
                dish.setId(id);
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
        return dish;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM dishes WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
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
        return false;
    }

    @Override
    public boolean create(Dish dish) {
        String query = "INSERT INTO dishes(name, proteins, carbohydrates, fats, dish_type_id) " +
                "VALUES(?,?,?,?,?);";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dish.getName());
            preparedStatement.setDouble(2, dish.getNutrients().getProteins());
            preparedStatement.setDouble(3, dish.getNutrients().getCarbohydrates());
            preparedStatement.setDouble(4, dish.getNutrients().getFats());
            preparedStatement.setInt(5, dish.getDishTypeId());
            preparedStatement.execute();

            dish.setId(getLastInsertedId());
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
    public Dish update(Dish dish, int key) {
        String query = "UPDATE dishes SET name = ?, proteins = ?, carbohydrates = ?, fats = ?, " +
                "dish_type_id = ? WHERE id = ?;";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dish.getName());
            preparedStatement.setDouble(2, dish.getNutrients().getProteins());
            preparedStatement.setDouble(3, dish.getNutrients().getCarbohydrates());
            preparedStatement.setDouble(4, dish.getNutrients().getFats());
            preparedStatement.setInt(5, dish.getDishTypeId());
            preparedStatement.setInt(6, key);
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
        return dish;
    }

    public int getLastInsertedId(){
        String query = "SELECT MAX(ID) AS max_id FROM dishes;";
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
        } finally {
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
