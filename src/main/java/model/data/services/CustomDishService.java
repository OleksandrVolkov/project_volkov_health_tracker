package model.data.services;

import model.data.dao.CustomDishDAO;
import model.entities.CustomDish;

import java.util.List;

public class CustomDishService {
    private CustomDishDAO customDishDAO;

    public CustomDishService(){
        customDishDAO = new CustomDishDAO();
    }

    public List<CustomDish> findAllCustomDishes() {
        return customDishDAO.findAll();
    }

    public CustomDish findCustomDishById(int id){
        return customDishDAO.findEntityById(id);
    }

    public boolean deleteCustomDishById(int id){
        return customDishDAO.delete(id);
    }

    public boolean createCustomDish(CustomDish customDish){
        return customDishDAO.create(customDish);
    }

    public CustomDish updateCustomDishById(CustomDish customDish, int id){
        return customDishDAO.update(customDish, id);
    }

    public List<CustomDish> getCustomDishesByUserId(int userId){
        return customDishDAO.getCustomDishesByUserId(userId);
    }


}
