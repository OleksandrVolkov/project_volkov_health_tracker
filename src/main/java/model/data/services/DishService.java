package model.data.services;

import model.data.dao.DishDAO;
import model.entities.Dish;

import java.util.List;

public class DishService {
    private DishDAO dishDAO;

    public DishService(){
        dishDAO = new DishDAO();
    }

    public List<Dish> findAllDishes() {
        return dishDAO.findAll();
    }

    public Dish findDishById(int id){
        return dishDAO.findEntityById(id);
    }

    public boolean deleteDishById(int id){
        return dishDAO.delete(id);
    }

    public boolean createDish(Dish dish){
        return dishDAO.create(dish);
    }

    public Dish updateDishById(Dish dish, int id){
        return dishDAO.update(dish, id);
    }


}
