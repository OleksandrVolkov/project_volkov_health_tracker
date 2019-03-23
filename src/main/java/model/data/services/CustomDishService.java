package model.data.services;

import model.data.dao.CustomDishDAO;
import model.entities.CustomDish;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * <h1>CustomDishService class</h1>
 * CustomDishService provides us an additional layer of abstraction in application to access to the
 * database. There are the vast majority methods that are used in CustomDishService class.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class CustomDishService {
    private static Logger log = Logger.getLogger(CustomDishService.class);
    private CustomDishDAO customDishDAO;

    public CustomDishService(){
        customDishDAO = new CustomDishDAO();
    }

    /**
     * This method is used to find all custom dishes from the corresponding table in the
     * database.
     * @return List of all of the custom dishes available in the table.
     */
    public List<CustomDish> findAllCustomDishes() {
        log.info("Finding all custom dishes");
        return customDishDAO.findAll();
    }

    /**
     * This method is used to find custom dish by its id in the database.
     * @param id This is the id of the custom dish is needed to find.
     * @return Dish It returns the custom dish by the given id.
     */
    public CustomDish findCustomDishById(int id){
        log.info("Finding custom dishes by id");
        return customDishDAO.findEntityById(id);
    }

    /**
     * This method is used to delete custom dish by its id.
     * @param id This is id of the custom dish is needed to delete.
     * @return boolean It returns the boolean value depending on whether custom dish was successfully deleted.
     * (by given id)
     */
    public boolean deleteCustomDishById(int id){
        log.info("Deleting custom dish by id = " + id);
        return customDishDAO.delete(id);
    }

    /**
     * This method is used to create custom dish by given object of the corresponding class.
     * @param customDish This is the item which values will be inserted into the table "custom_dishes".
     * @return boolean It returns the boolean value depending on whether custom dish was successfully created.
     */
    public boolean createCustomDish(CustomDish customDish){
        log.info("Create custom dish: " + customDish);
        return customDishDAO.create(customDish);
    }

    /**
     * This method is used to update custom dish value in the database by its key.
     * @param customDish This is the custom dish which values will be inserted into the table "custom_dishes"
     * @param id This is the id which is used to update the corresponding value
     * @return CustomDish It returns given dish
     */
    public CustomDish updateCustomDishById(CustomDish customDish, int id){
        log.info("Updating custom dish by id = " + id);
        return customDishDAO.update(customDish, id);
    }

    /**
     * This method is used to find all custom dishes from the corresponding table in the
     * database by the given user id.
     * @param userId Id of the user
     * @return List of all of the custom dishes available by the given user id in the table.
     */
    public List<CustomDish> getCustomDishesByUserId(int userId){
        log.info("Getting custom dishes by user id = " + userId);
        return customDishDAO.getCustomDishesByUserId(userId);
    }
}
