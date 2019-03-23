package model.data.dao.dao_interfaces;

import model.entities.CustomDish;

import java.util.List;

/**
 *  <h1>CustomDishDAO interface</h1>
 *  CustomDishDAO interface is an interface that includes all CRUD (create,
 *  read, update, delete) operations (via extending AbstractDAO interface) pre-defined in the
 *  base AbstractDAO class and adds some more functionality.
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public interface CustomDishDAO extends AbstractDAO<CustomDish>{
    /**
     * This method is used to find all custom dishes from the corresponding table in the
     * database.
     * @param userId This is the user id of the User this custom dish belongs.
     * @return List of all of the custom dishes are belonged to to the user.
     */
    List<CustomDish> getCustomDishesByUserId(int userId);

    /**
     * This method is used to get the last inserted custom dish id.
     * @return Id of the last inserted custom dish.
     */
    Integer getLastInsertedCustomDishId();
}
