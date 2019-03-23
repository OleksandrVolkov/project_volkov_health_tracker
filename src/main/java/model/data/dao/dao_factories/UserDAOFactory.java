package model.data.dao.dao_factories;

import model.data.dao.dao_implementations.mysql_dao.MySQLUserDAO;
import model.data.dao.dao_interfaces.UserDAO;
import model.entities.enums.DatabaseType;

/**
 *  <h1>UserDAOFactory</h1>
 *  UserDAOFactory is an implementation of the GoF pattern called Factory (which is
 *  used to create objects) to create the relative implementation of the databse type of the UserDAO
 *  class.
 *
 *
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class UserDAOFactory {
    /**
     * This method is used to crete a relative to the database type instance of the UserDAO.
     * @return UserDAO the relative to the database type instance of the UserDAO.
     */
    public static UserDAO getUserDAO(DatabaseType databaseType){
        if (databaseType.equals(DatabaseType.MY_SQL)){
            return new MySQLUserDAO();
        }else{
            return null;
        }
    }
}
