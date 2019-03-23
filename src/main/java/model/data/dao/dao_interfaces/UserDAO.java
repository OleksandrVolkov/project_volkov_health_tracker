package model.data.dao.dao_interfaces;

import model.entities.User;

/**
 *  <h1>UserDAO interface</h1>
 *  UserDAO interface is an interface that includes all CRUD (create,
 *  read, update, delete) operations (via extending AbstractDAO interface) pre-defined in the
 *  base AbstractDAO class and adds some more functionality.
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public interface UserDAO extends AbstractDAO<User> {
    boolean verify(String username, String password);
    /**
     * This method is used to find out whether the email was already taken by another user.
     * @param username Username of the user is searched for.
     * @return boolean Whether username is already taken by another user.
     */
    boolean isUsernameTaken(String username);
    /**
     * This method is used to find out whether the email was already taken by another user.
     * @param email Email of the user is searched for.
     * @return boolean Whether email is already taken by another user.
     */
    boolean isEmailTaken(String email);

    /**
     * This method is used to find user by his/her username.
     * @param username Username of the user searched for
     * @return User found by the given username
     */
    User findUserByUsername(String username);

    /**
     * This method is used to get the last inserted user id.
     * @return Id of the last inserted user.
     */
    Integer getLastInsertedUserId();
}
