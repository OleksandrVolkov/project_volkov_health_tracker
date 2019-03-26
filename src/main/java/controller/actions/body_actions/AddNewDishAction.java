package controller.actions.body_actions;

import controller.actions.Action;
import controller.actions.account_actions.ValidateFormAction;
import model.data.services.CustomDishService;
import model.data.services.DishTypeService;
import model.data.services.UserService;
import model.entities.CustomDish;
import model.entities.DishType;
import model.entities.Nutrients;
import model.entities.User;
import org.apache.log4j.Logger;

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
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(AddNewDishAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Adding new dish");
        log.trace("Getting parameters from the request");
        String name = request.getParameter("add_dish_input");
        String dishTypeIdStr = request.getParameter("dish_type");
        String proteinsStr = request.getParameter("proteins");
        String carbohydratesStr = request.getParameter("carbohydrates");
        String fatsStr = request.getParameter("fats");


        HttpSession httpSession = request.getSession();
        String username = (String)httpSession.getAttribute("LOGGED_USER");
        log.trace("Getting username from the session: " + username);
        UserService userService = new UserService();
        log.trace("Finding user by username");
        User user = userService.findUserByUsername(username);

         CustomDish customDish = new CustomDish();


        Integer dishTypeId = null;
        double proteins = 0.0;
        double carbohydrates = 0.0;
        double fats = 0.0;
        log.trace("Parsing double and integer values");
        if(!proteinsStr.equals(""))
            proteins = Double.parseDouble(proteinsStr);
        if(!carbohydratesStr.equals(""))
            carbohydrates = Double.parseDouble(carbohydratesStr);
        if(!fatsStr.equals(""))
            fats = Double.parseDouble(fatsStr);

        if(!dishTypeIdStr.equals(""))
             dishTypeId = Integer.parseInt(dishTypeIdStr);
        if(name == null || name.equals("")){
            log.warn("Name is not present");
        }
        else {
            log.trace("Setting custom dish values");
            customDish.setName(name);
            customDish.setUserId(user.getId());
            customDish.setDishTypeId(dishTypeId);
            customDish.setNutrients(new Nutrients(proteins, carbohydrates, fats));
            CustomDishService customDishService = new CustomDishService();
            log.trace("Creating custom dish");
            customDishService.createCustomDish(customDish);
        }

        String url = "/load_data?action=load_to_add";
        log.trace("Returning url " + url);
        return url;
    }
}
