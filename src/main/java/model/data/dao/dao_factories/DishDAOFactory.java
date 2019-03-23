package model.data.dao.dao_factories;

import model.data.dao.dao_implementations.mysql_dao.MySQLDishDAO;
import model.data.dao.dao_interfaces.DishDAO;
import model.entities.enums.DatabaseType;

/**
 *  <h1>DishDAOFactory</h1>
 *  DishDAOFactory is an implementation of the GoF pattern called Factory (which is
 *  used to create objects) to create the relative implementation of the databse type of the DishDAO
 *  class.
 *
 *
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class DishDAOFactory {
    /**
     * This method is used to crete a relative to the database type instance of the DishDAO.
     * @return DishDAO the relative to the database type instance of the DishDAO.
     */
    public static DishDAO getDishDAO(DatabaseType databaseType){
        if (databaseType.equals(DatabaseType.MY_SQL)){
            return new MySQLDishDAO();
        }else{
            return null;
        }
    }
}
