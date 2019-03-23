package model.data.services;

import model.data.dao.dao_factories.DishTypeDAOFactory;
import model.data.dao.dao_implementations.mysql_dao.MySQLDishTypeDAO;
import model.data.dao.dao_interfaces.DishTypeDAO;
import model.entities.DishType;
import model.entities.enums.DatabaseType;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * <h1>DishTypeService class</h1>
 * DishTypeService provides us an additional layer of abstraction in application to access to the
 * database. There are the vast majority methods that are used in MySQLDishTypeDAO class.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class DishTypeService {
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(DishTypeService.class);
    /**
     * This is the object of relative to this class lower layer of abstraction in the application - DishTypeDAO
     */
    private DishTypeDAO dishTypeDAO;

    /**
     * Constructor initialises the relative DAO object via DishTypeDAOFactory class.
     */
    public DishTypeService(){
        dishTypeDAO = DishTypeDAOFactory.getDishTypeDAO(DatabaseType.MY_SQL);
    }

    /**
     * This method is used to find all dish types from the corresponding table in the
     * database.
     * @return List of all of the dish types available in the table.
     */
    public List<DishType> findAllDishTypes() {
        log.info("Finding all dish types");
        return dishTypeDAO.findAll();
    }

    /**
     * This method is used to find dish type by its id in the database.
     * @param id This is the id of the dish type is needed to find.
     * @return DishType It returns the dish type by the given id.
     */
    public DishType findDishTypeById(int id){
        log.info("Finding dish type by id = " + id);
        return dishTypeDAO.findEntityById(id);
    }

    /**
     * This method is used to delete dish type by its id.
     * @param id This is id of the dish type is needed to delete.
     * @return boolean It returns the boolean value depending on whether dish type was successfully deleted.
     * (by given id)
     */
    public boolean deleteDishTypeById(int id){
        log.info("Deleting dish type by id = " + id);
        return dishTypeDAO.delete(id);
    }

    /**
     * This method is used to create dish type by given object of the corresponding class.
     * @param dishType This is the item which values will be inserted into the table "dish_types".
     * @return boolean It returns the boolean value depending on whether dish type was successfully created.
     */
    public boolean createDishType(DishType dishType){
        log.info("Creating dish type: " + dishType);
        return dishTypeDAO.create(dishType);
    }

    /**
     * This method is used to update dish type value in the database by its key.
     * @param dishType This is the dishType which values will be inserted into the table "dish_types"
     * @param id This is the id which is used to update the corresponding value
     * @return DishType It returns given user
     */
    public DishType updateDishTypeById(DishType dishType, int id){
        log.info("Updating dish type by id = " + id);
        return dishTypeDAO.update(dishType, id);
    }
}
