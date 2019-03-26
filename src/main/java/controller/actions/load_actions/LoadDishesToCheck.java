package controller.actions.load_actions;

import controller.actions.Action;
import controller.actions.account_actions.ValidateFormAction;
import controller.utility.LanguageHandler;
import model.data.dao.dao_implementations.mysql_dao.MySQLDishTypeDAO;
import model.data.services.CustomDishService;
import model.data.services.DishService;
import model.data.services.UserService;
import model.entities.CustomDish;
import model.entities.Dish;
import model.entities.DishType;
import model.entities.enums.Language;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LoadDishesToCheck implements Action{
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(LoadDishesToCheck.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Loading dishes to check");
        DishService dishService = new DishService();
        CustomDishService customDishService = new CustomDishService();
        UserService userService = new UserService();

        log.trace("Finding all dishes");
        List<Dish> dishes = dishService.findAllDishes();

        HttpSession httpSession = request.getSession();
        String username = (String)httpSession.getAttribute("LOGGED_USER");
        List<CustomDish> customDishes = customDishService.getCustomDishesByUserId(userService.findUserByUsername(username).getId());
        dishes.addAll(customDishes);




        Map<Dish, String> dishesMap = new TreeMap<>();
        MySQLDishTypeDAO mySQLDishTypeDAO = new MySQLDishTypeDAO();


        for(Dish dish: dishes){
            DishType dishType = mySQLDishTypeDAO.findEntityById(dish.getDishTypeId());
            dishesMap.put(dish, dishType.getName());
        }


        request.setAttribute("dishes", dishesMap);









        String url = "/view/check_diet.jsp";
        LanguageHandler languageHandler = new LanguageHandler();
        String lang = languageHandler.getLangValue(request, request.getParameter("lang"));
        Map<String, String> dishesMapToCheck = LanguageHandler.getHashMapOfValuesByPageUrl(url, Language.getLanguage(lang));

        request.setAttribute("language", dishesMapToCheck);
        return url;
    }
}
