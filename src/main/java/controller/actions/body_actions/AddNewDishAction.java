package controller.actions.body_actions;

import controller.actions.Action;
import model.data.services.CustomDishService;
import model.data.services.DishTypeService;
import model.data.services.UserService;
import model.entities.CustomDish;
import model.entities.DishType;
import model.entities.Nutrients;
import model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  <h1>AddNewDishAction class</h1>
 *  AddNewDishAction is an implementation class of the Action interface.
 *  It adds a new custom dish to the personal cabinet of the user.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class AddNewDishAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("add_dish_input");
        String dishTypeIdStr = request.getParameter("dish_type");

        HttpSession httpSession = request.getSession();
        String username = (String)httpSession.getAttribute("LOGGED_USER");
        UserService userService = new UserService();
        User user = userService.findUserByUsername(username);

         CustomDish customDish = new CustomDish();


        Integer dishTypeId = null;
        if(!dishTypeIdStr.equals(""))
             dishTypeId = Integer.parseInt(dishTypeIdStr);
        if(name == null || name.equals("")){
            System.out.println("ERRRORROROROR!!!");
        }
        else {
            customDish.setName(name);
            customDish.setUserId(user.getId());
            customDish.setDishTypeId(dishTypeId);
            customDish.setNutrients(new Nutrients(230, 134, 532));
            CustomDishService customDishService = new CustomDishService();
            customDishService.createCustomDish(customDish);
        }



        return "/body?action=load_to_add";
    }
}
