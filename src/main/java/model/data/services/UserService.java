package model.data.services;

import model.data.dao.dao_factories.UserDAOFactory;
import model.data.dao.dao_implementations.mysql_dao.MySQLUserDAO;
import model.data.dao.dao_interfaces.UserDAO;
import model.entities.User;
import model.entities.enums.DatabaseType;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * <h1>UserService class</h1>
 * UserService provides us an additional layer of abstraction in application to access to the
 * database. There are the vast majority methods that are used in MySQLUserDAO class.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class UserService {
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(DishTypeService.class);
    /**
     * This is the object of relative to this class lower layer of abstraction in the application - UserDAO
     */
    private UserDAO userDAO;

    /**
     * Constructor initialises the relative DAO object via UserDAOFactory class.
     */
    public UserService(){
        userDAO = UserDAOFactory.getUserDAO(DatabaseType.MY_SQL);
    }


    /**
     * This method is used to find all users from the corresponding table in the
     * database.
     * @return List of all of the users available in the table.
     */
    public List<User> findAllUsers() {
        log.info("Finding all users");
        return userDAO.findAll();
    }

    /**
     * This method is used to find user by its id in the database.
     * @param id This is the id of the user is needed to find.
     * @return User It returns the user by the given id.
     */
    public User findUserById(int id){
        log.info("Finding user by id = " + id);
        return userDAO.findEntityById(id);
    }

    /**
     * This method is used to delete user by its id.
     * @param id This is id of the user is needed to delete.
     * @return boolean It returns the boolean value depending on whether user was deleted.
     * (by given id)
     */
    public boolean deleteUserById(int id){
        log.info("Deleting user by id = " + id);
        return userDAO.delete(id);
    }

    /**
     * This method is used to create user by given object of the corresponding class.
     * @param user This is the item which values will be inserted into the table "users".
     * @return boolean It returns the boolean value depending on whether user was successfully created.
     */
    public boolean createUser(User user){
        log.info("Creating user: " + user);
        return userDAO.create(user);
    }

    /**
     * This method is used to update user value in the database by its key.
     * @param user This is the user which values will be inserted into the table "users"
     * @param id This is the id which is used to update the corresponding value
     * @return User It returns given user
     */
    public User updateUserById(User user, int id){
        log.info("Updating user by id = " + id);
        return userDAO.update(user, id);
    }

    /**
     * This method is used to validate the user value in the database by its username and password.
     * @param username This is the username of the user
     * @param password This is the password of the user
     * @return boolean It returns the boolean value depending on whether user was successfully validated.
     */
    public boolean verifyUser(String username, String password){
        log.info("Verifying user with username = " + username);
        return userDAO.verify(username, password);
    }

    /**
     * This method is used to check whether username was already taken.
     * @param username This is the username of the user
     * @return boolean It returns the boolean value depending on whether username was already taken by somebody.
     */
    public boolean isUsernameTaken(String username){
        log.info("Verifying whether username is taken");
        return userDAO.isUsernameTaken(username);
    }

    /**
     * This method is used to check whether email was already taken.
     * @param email This is the email of the user
     * @return boolean It returns the boolean value depending on whether email was already taken by somebody.
     */
    public boolean isEmailTaken(String email){
        log.info("Verifying whether email is taken");
        return userDAO.isEmailTaken(email);
    }

    /**
     * This method is used to find a user by its username.
     * @param username This is the username of the supposed user
     * @return User It returns the user value fond by the given username
     */
    public User findUserByUsername(String username){
        log.info("Finding user by username = " + username);
        return userDAO.findUserByUsername(username);
    }
}
