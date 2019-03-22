package model.data.dao;

import model.entities.Dish;
import model.entities.DishType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishTypeDAO extends AbstractDAO<DishType> {
    @Override
    public List<DishType> findAll() {
        List<DishType> dishTypes = new ArrayList<>();
        String query = "SELECT * FROM dish_types";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                DishType dishType = new DishType(name);
                dishType.setId(id);
                dishTypes.add(dishType);
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

        return dishTypes;
    }

    @Override
    public DishType findEntityById(int id) {
        String query = "SELECT * FROM dish_types WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DishType dishType = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                dishType = new DishType(name);
                dishType.setId(id);
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


        return dishType;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM dish_types WHERE id = ?";
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
    public boolean create(DishType dishType) {
        String query = "INSERT INTO dish_types(name) VALUES(?);";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dishType.getName());
            preparedStatement.execute();

            Integer id = getLastInsertedDishTypeId();
            dishType.setId(id);
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
    public DishType update(DishType dishType, int key) {
        String query = "UPDATE dish_types SET name = ? WHERE id = ?;";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dishType.getName());
            preparedStatement.setInt(2, key);

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
        return dishType;
    }


    public synchronized Integer getLastInsertedDishTypeId(){
        String query = "SELECT MAX( id ) AS max_id FROM dish_types;";
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
