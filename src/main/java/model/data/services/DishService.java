package model.data.services;

import model.data.dao.DishDAO;
import model.entities.Dish;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * <h1>DishService class</h1>
 * DishService provides us an additional layer of abstraction in application to access to the
 * database. There are the vast majority methods that are used in DishService class.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class DishService {
    private static Logger log = Logger.getLogger(CustomDishService.class);
    private DishDAO dishDAO;

    public DishService(){
        dishDAO = new DishDAO();
    }

    /**
     * This method is used to find all dishes from the corresponding table in the
     * database.
     * @return List of all of the dishes available in the table.
     */
    public List<Dish> findAllDishes() {
        log.info("Finding all dishes");
        return dishDAO.findAll();
    }

    /**
     * This method is used to find dish by its id in the database.
     * @param id This is the id of the dish is needed to find.
     * @return Dish It returns the dish by the given id.
     */
    public Dish findDishById(int id){
        log.info("Finding dish by id = " + id);
        return dishDAO.findEntityById(id);
    }

    /**
     * This method is used to delete dish by its id.
     * @param id This is id of the dish is needed to delete.
     * @return boolean It returns the boolean value depending on whether dish was successfully deleted.
     * (by given id)
     */
    public boolean deleteDishById(int id){
        log.info("Deleting dish by id = " + id);
        return dishDAO.delete(id);
    }

    /**
     * This method is used to create dish by given object of the corresponding class.
     * @param dish This is the item which values will be inserted into the table "dishes".
     * @return boolean It returns the boolean value depending on whether dish was successfully created.
     */
    public boolean createDish(Dish dish){
        log.info("Creating dish: " + dish);
        return dishDAO.create(dish);
    }

    /**
     * This method is used to update dish value in the database by its id.
     * @param dish This is the dish which values will be inserted into the table "dishes"
     * @param id This is the id which is used to update the corresponding value
     * @return Dish It returns given dish
     */
    public Dish updateDishById(Dish dish, int id){
        log.info("Updating dish by id = " + id);
        return dishDAO.update(dish, id);
    }
}
