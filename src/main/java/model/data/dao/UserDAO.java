package model.data.dao;

import model.entities.User;
import model.entities.enums.Lifestyle;
import model.entities.enums.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users;";
        User user = null;
        PreparedStatement  preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                double height = resultSet.getDouble("height");
                double weight = resultSet.getDouble("weight");
                String email = resultSet.getString("email");
                String sexStr = resultSet.getString("sex");
                Sex sex = Sex.lookup(sexStr);
                Lifestyle lifestyle = Lifestyle.lookup(resultSet.getString("lifestyle"));


                user = new User.Builder().withId(id)
                                .withAge(age)
                                .withEmail(email)
                                .withHeight(height)
                                .withLifestyle(lifestyle)
                                .withName(name)
                                .withSurname(surname)
                                .withPassword(password)
                                .withWeight(weight)
                                .withSex(sex)
                                .withUsername(username)
                                .build();

                users.add(user);
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
        return users;
    }

    @Override
    public User findEntityById(int id) {
        String query = "SELECT * FROM users WHERE id = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                double height = resultSet.getDouble("height");
                double weight = resultSet.getDouble("weight");
                Lifestyle lifestyle = Lifestyle.lookup(resultSet.getString("lifestyle"));
                String email = resultSet.getString("email");
                String sexStr = resultSet.getString("sex");
                Sex sex = Sex.lookup(sexStr);


                user = new User.Builder().withId(id)
                        .withAge(age)
                        .withEmail(email)
                        .withHeight(height)
                        .withLifestyle(lifestyle)
                        .withName(name)
                        .withSurname(surname)
                        .withPassword(password)
                        .withWeight(weight)
                        .withSex(sex)
                        .withUsername(username)
                        .build();
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
        return user;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
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
        return false;
    }

    @Override
    public synchronized boolean create(User user) {

        String query = "INSERT INTO users(name, surname, username, email, password, height, weight, lifestyle, age, sex) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement preparedStatement = null;


        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setDouble(6, user.getHeight());
            System.out.println("HEIGHT ACTUALLY: " + user.getHeight());
            preparedStatement.setDouble(7, user.getWeight());
            preparedStatement.setString(8, user.getLifestyle().toString());
            preparedStatement.setInt(9, user.getAge());
            preparedStatement.setString(10, user.getSex().toString());

            preparedStatement.execute();

            Integer id = getLastInsertedUserId();
            user.setId(id);
            System.out.println("USER ID = " + user.getId());
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
    public User update(User user, int key) {
        String query = "UPDATE users SET name = ?, surname = ?, email = ?, username = ?, password = ?," +
                " height = ?, weight = ?, lifestyle = ?, age = ?, sex = ? WHERE id = ?;";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setDouble(6, user.getHeight());
            preparedStatement.setDouble(7, user.getWeight());
            preparedStatement.setString(8, user.getLifestyle().toString());
            preparedStatement.setInt(9, user.getAge());
            preparedStatement.setString(10, user.getSex().toString());
            preparedStatement.setInt(11, key);

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
        return user;
    }

    public boolean verify(String username, String password){
        String query = "SELECT * FROM users WHERE username = ? AND password = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
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

    public boolean isUsernameTaken(String username){
        String query = "SELECT * FROM users WHERE username = ?;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
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
        return false;
    }

    public boolean isEmailTaken(String email){
        String query = "SELECT * FROM users WHERE email = ?;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
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
        return false;
    }

    public User findUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";

        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                double height = resultSet.getDouble("height");
                double weight = resultSet.getDouble("weight");
                Lifestyle lifestyle = Lifestyle.lookup(resultSet.getString("lifestyle"));
                String email = resultSet.getString("email");
                String sexStr = resultSet.getString("sex");
                Sex sex = Sex.lookup(sexStr);

                user = new User.Builder().withId(id)
                        .withAge(age)
                        .withEmail(email)
                        .withHeight(height)
                        .withLifestyle(lifestyle)
                        .withName(name)
                        .withSurname(surname)
                        .withPassword(password)
                        .withWeight(weight)
                        .withSex(sex)
                        .withUsername(username)
                        .build();

                return user;
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
        return null;
    }


    public synchronized Integer getLastInsertedUserId(){
        String query = "SELECT MAX( id ) AS max_id FROM users;";
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
