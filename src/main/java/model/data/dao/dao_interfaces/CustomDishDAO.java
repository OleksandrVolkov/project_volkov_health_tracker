package model.data.dao.dao_interfaces;

import model.data.dao.AbstractDAO;
import model.entities.CustomDish;
import model.entities.Entity;

import java.util.List;

public interface CustomDishDAO{
    List<CustomDish> getCustomDishesByUserId(int userId);
    Integer getLastInsertedCustomDishId();
}
