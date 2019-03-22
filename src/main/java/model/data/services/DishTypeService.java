package model.data.services;

import model.data.dao.DishTypeDAO;
import model.entities.DishType;

import java.util.List;

public class DishTypeService {
    private DishTypeDAO dishTypeDAO;

    public DishTypeService(){
        dishTypeDAO = new DishTypeDAO();
    }

    public List<DishType> findAllDishTypes() {
        return dishTypeDAO.findAll();
    }

    public DishType findDishTypeById(int id){
        return dishTypeDAO.findEntityById(id);
    }

    public boolean deleteDishTypeById(int id){
        return dishTypeDAO.delete(id);
    }

    public boolean createDishType(DishType customDish){
        return dishTypeDAO.create(customDish);
    }

    public DishType updateDishTypeById(DishType dishType, int id){
        return dishTypeDAO.update(dishType, id);
    }
}
