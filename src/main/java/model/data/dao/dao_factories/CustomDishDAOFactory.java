package model.data.dao.dao_factories;

import model.data.dao.dao_implementations.mysql_dao.MySQLCustomDishDAO;
import model.data.dao.dao_interfaces.CustomDishDAO;
import model.entities.enums.DatabaseType;

/**
 *  <h1>CustomDishDAOFactory</h1>
 *  CustomDishDAOFactory is an implementation of the GoF pattern called Factory (which is
 *  used to create objects) to create the relative implementation of the databse type of the CustomDishDAO
 *  class.
 *
 *
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */

public class CustomDishDAOFactory {
    /**
     * This method is used to crete a relative to the database type instance of the CustomDishDAO.
     * @return CustomDishDAO the relative to the database type instance of the CustomDishDAO.
     */
    public static CustomDishDAO getCustomDishDAO(DatabaseType databaseType){
        if (databaseType.equals(DatabaseType.MY_SQL)){
            return new MySQLCustomDishDAO();
        }else{
            return null;
        }
    }
}
