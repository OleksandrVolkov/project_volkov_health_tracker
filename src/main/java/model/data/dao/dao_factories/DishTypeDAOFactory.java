package model.data.dao.dao_factories;

import model.data.dao.dao_implementations.mysql_dao.MySQLDishTypeDAO;
import model.data.dao.dao_interfaces.DishTypeDAO;
import model.entities.enums.DatabaseType;

/**
 *  <h1>DishTypeDAOFactory</h1>
 *  DishTypeDAOFactory is an implementation of the GoF pattern called Factory (which is
 *  used to create objects) to create the relative implementation of the databse type of the DishTypeDAO
 *  class.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class DishTypeDAOFactory {
    /**
     * This method is used to crete a relative to the database type instance of the DishTypeDAO.
     * @return DishTypeDAO the relative to the database type instance of the DishTypeDAO.
     */
    public static DishTypeDAO getDishTypeDAO(DatabaseType databaseType){
        if (databaseType.equals(DatabaseType.MY_SQL)){
            return new MySQLDishTypeDAO();
        }else{
            return null;
        }
    }
}
