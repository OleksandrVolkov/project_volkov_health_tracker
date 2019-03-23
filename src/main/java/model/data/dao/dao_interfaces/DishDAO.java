package model.data.dao.dao_interfaces;

import model.entities.Dish;

/**
 *  <h1>DishDAO interface</h1>
 *  DishDAO interface is an interface that includes all CRUD (create,
 *  read, update, delete) operations (via extending AbstractDAO interface) pre-defined in the
 *  base AbstractDAO class and adds some more functionality.
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public interface DishDAO extends AbstractDAO<Dish> {
    /**
     * This method is used to get the last inserted dish id.
     * @return Id of the last inserted dish.
     */
    Integer getLastInsertedId();
}
