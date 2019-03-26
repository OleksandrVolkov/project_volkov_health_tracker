package model.data.dao.dao_implementations.mysql_dao;

import model.data.dao.connection.ConnectionPool;
import model.data.dao.dao_interfaces.UserDAO;
import model.entities.User;
import model.entities.enums.Lifestyle;
import model.entities.enums.Sex;
import controller.utility.MD5Handler;
import org.apache.log4j.Logger;
import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>MySQLUserDAO class</h1>
 *  MySQLUserDAO represents a way to access database to the corresponding to the
 *  User entity table "users" via JDBC API by SQL server using MySQL implementation.
 *  It represents the way to access to the value needed and make basic CRUD (create,
 *  read, update, delete) operations and some more added functionality.
 *  Moreover, it gives the opportunity to initialize the entity
 *  objects(User class) on the side of model which makes it easier to manipulate with the objects
 *  in the application in the object-oriented way.
 *  It implements a UserDAO interface and therefore overrides some its methods.
 *
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */

public class MySQLUserDAO implements UserDAO {
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(MySQLUserDAO.class);
    /**
     * ConnectionPool to handle multiple connections from various threads
     */
    private ConnectionPool connectionPool;

    /**
     * Constructor of the class to instantiate connectionPool field
     */
    public MySQLUserDAO(){
        connectionPool = new ConnectionPool();
    }

    /**
     * This method is used to find all users from the corresponding table in the
     * database.
     * @return List of all of the users available in the table.
     */
    @Override
    public List<User> findAll() {
        log.info("Finding all users");
        String query = "SELECT * FROM users;";
        User user = null;
        PreparedStatement  preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connectionPool.printDbStatus();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            log.trace("Creating result set");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                log.trace("Getting values from result set");
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
                log.trace("Obtained user: " + user);
                log.trace("Adding to the list");
                users.add(user);
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
                log.trace("Can't close result set: " + e);
                log.trace("Can't close prepared statement: " + e);
            }
        }
        return users;
    }

    /**
     * This method is used to find user by its id in the database.
     * @param id This is the id of the user is needed to find.
     * @return User It returns the user by the given id.
     */
    @Override
    public User findEntityById(int id) {
        log.info("Finding user by id = " + id);
        String query = "SELECT * FROM users WHERE id = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connectionPool.printDbStatus();
            System.out.println("CREATING CONNECTIONS");
            log.trace("Creating connection");
//            connection = connectionManager.getConnection();
            connection = dataSource.getConnection();
            connectionPool.printDbStatus();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            log.trace("Creating result set");
            resultSet = preparedStatement.executeQuery();
            System.out.println("BEFORE IF");
            if (resultSet.next()){
                System.out.println("INTO IF!!!!!!");
                log.trace("Getting values from result set");
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
                System.out.println("UUUSSSEEERR:  " + user);
                log.trace("Obtained user: " + user);
            }
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
            System.err.println(e);
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
                log.trace("Can't close result set: " + e);
                log.trace("Can't close prepared statement: " + e);
            }
        }
        return user;
    }

    /**
     * This method is used to delete user by its id.
     * @param id This is id of the user is needed to delete.
     * @return boolean It returns the boolean value depending on whether user was deleted.
     * (by given id)
     */
    @Override
    public boolean delete(int id) {
        log.info("Deleting user by id = " + id);
        String query = "DELETE FROM users WHERE id = ?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connectionPool.printDbStatus();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
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
                log.trace("Can't close prepared statement: " + e);
            }
        }
        return false;
    }

    /**
     * This method is used to create user by given object of the corresponding class.
     * @param user This is the item which values will be inserted into the table "users".
     * @return boolean It returns the boolean value depending on whether user was successfully created.
     */
    @Override
    public synchronized boolean create(User user) {
        log.info("Creating a new user: " + user);
        String query = "INSERT INTO users(name, surname, username, email, password, height, weight, lifestyle, age, sex) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connectionPool.printDbStatus();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            connectionPool.printDbStatus();

            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getEmail());
            String MD5password = MD5Handler.md5Custom(user.getPassword());
            //!!!!!!!!!!!!!!!!!!
            user.setPassword(MD5password);
            preparedStatement.setString(5, MD5password);
            preparedStatement.setDouble(6, user.getHeight());
            preparedStatement.setDouble(7, user.getWeight());
            preparedStatement.setString(8, user.getLifestyle().toString());
            preparedStatement.setInt(9, user.getAge());
            preparedStatement.setString(10, user.getSex().toString());

            preparedStatement.execute();

            Integer id = getLastInsertedUserId();
            user.setId(id);
            log.trace("User with id = " + id + "is created");
            return true;
        } catch (SQLException e) {
            log.warn("SQL exception caught: " + e);
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                log.trace("Can't close prepared statement: " + e);
            }
        }
        return false;
    }

    /**
     * This method is used to update user value in the database by its key.
     * @param user This is the user which values will be inserted into the table "users"
     * @param key This is the id which is used to update the corresponding value
     * @return User It returns given user
     */
    @Override
    public User update(User user, int key) {
        log.info("Updating user by id = " + key);
        String query = "UPDATE users SET name = ?, surname = ?, email = ?, username = ?, password = ?," +
                " height = ?, weight = ?, lifestyle = ?, age = ?, sex = ? WHERE id = ?;";

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connectionPool.printDbStatus();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
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
            log.trace("User with id = " + key + " is created");
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
                log.trace("Can't close prepared statement: " + e);
            }
        }
        return user;
    }


    //TODO:: RENAME??!!?!?
    /**
     * This method is used to validate the user value in the database by its username and password.
     * @param username This is the username of the user
     * @param password This is the password of the user
     * @return boolean It returns the boolean value depending on whether user was successfully validated.
     */
    public boolean verify(String username, String password){
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        log.info("Verifying user with username = " + username);
        String query = "SELECT * FROM users WHERE username = ? AND password = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connectionPool.printDbStatus();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            log.trace("Creating result set");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                log.trace("User is found");
                return true;
            }
            log.trace("User not found");
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
                log.trace("Can't close prepared statement: " + e);
                log.trace("Can't close result set: " + e);
            }
        }
        return false;
    }

    /**
     * This method is used to check whether username was already taken.
     * @param username This is the username of the user
     * @return boolean It returns the boolean value depending on whether username was already taken by somebody.
     */
    public boolean isUsernameTaken(String username){
        log.info("Verifying whether username [" + username + "] is already taken");
        String query = "SELECT * FROM users WHERE username = ?;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connectionPool.printDbStatus();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            log.trace("Creating result set");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                log.trace("Username is taken");
                return true;
            }
            log.trace("Username is not taken");
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
                log.trace("Can't close prepared statement: " + e);
                log.trace("Can't close result set: " + e);
            }
        }
        return false;
    }

    /**
     * This method is used to check whether email was already taken.
     * @param email This is the email of the user
     * @return boolean It returns the boolean value depending on whether email was already taken by somebody.
     */
    public boolean isEmailTaken(String email){
        log.info("Verifying whether email [" + email + "] is already taken");
        String query = "SELECT * FROM users WHERE email = ?;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connectionPool.printDbStatus();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            log.trace("Creating result set");
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                log.trace("Email is taken");
                return true;
            }
            log.trace("Email is not taken");
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
                log.trace("Can't close prepared statement: " + e);
                log.trace("Can't close result set: " + e);
            }
        }
        return false;
    }

    /**
     * This method is used to find a user by its username.
     * @param username This is the username of the supposed user
     * @return User It returns the user value fond by the given username
     */
    public User findUserByUsername(String username) {
        log.info("Finding user by username = " + username);
        String query = "SELECT * FROM users WHERE username = ?";

        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connectionPool.printDbStatus();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            log.trace("Creating result set");
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

                log.trace("User is found: " + user);
                return user;
            }
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
                log.trace("Can't close prepared statement: " + e);
                log.trace("Can't close result set: " + e);
            }
        }
        return null;
    }

    /**
     * This method is used to get index of the last inserted user.
     * It is mainly used while creating user so that get its index for
     * future handling in the application.
     * @return Integer It returns the index of the last inserted user.
     */
    public synchronized Integer getLastInsertedUserId(){
        log.trace("Getting last inserted user id");
        String query = "SELECT MAX( id ) AS max_id FROM users;";
        int id = 0;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connectionPool.printDbStatus();
            log.trace("Creating connection");
            connection = dataSource.getConnection();
            log.trace("Creating prepared statement");
            preparedStatement = connection.prepareStatement(query);
            log.trace("Creating result set");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("max_id");
            }
            log.trace("Obtained id = " + id);
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
                log.trace("Can't close prepared statement: " + e);
                log.trace("Can't close result set: " + e);
            }
        }
        return id;
    }
}
