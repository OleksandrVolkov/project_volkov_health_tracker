package controller.actions.load_actions;

import controller.actions.Action;
import model.data.services.DishTypeService;
import model.entities.DishType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoadToAddCustomDishAction implements Action{
//TODO:: CHANGE SESSIONS OR COOKIES!!!!! DON'T FORGET!!!!!!!!
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DishTypeService dishTypeService = new DishTypeService();
        List<DishType> dishTypes = dishTypeService.findAllDishTypes();
        request.setAttribute("dishTypes", dishTypes);
        return "/view/add_custom_dish.jsp";
    }
}
