package controller.actions.body_actions;

import controller.actions.Action;
import controller.utility.FoodCalculator;
import model.data.dao.dao_interfaces.AbstractDAO;
import model.data.dao.dao_implementations.mysql_dao.MySQLCustomDishDAO;
import model.data.dao.dao_implementations.mysql_dao.MySQLDishDAO;
import model.data.dao.dao_implementations.mysql_dao.MySQLUserDAO;
import model.data.services.UserService;
import model.entities.Dish;
import model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *  <h1>CheckDietAction class</h1>
 *  CheckDietAction is an implementation class of the Action interface.
 *  It checks diet by the filled values.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class CheckDietAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int i = 2;
        MySQLDishDAO mySQLDishDAO = new MySQLDishDAO();
        List<Dish> dishesList = new ArrayList<>();

        String val = request.getParameter("food_item_" + i);
        while(val != null){
            String notParsedVal = request.getParameter("food_item_" + i);

            String temp = notParsedVal.substring(0, 3);
            System.out.println(temp+" TEMP_____________________");
            AbstractDAO abstractDAO = null;
            if(temp.equals("cus"))
                abstractDAO = new MySQLCustomDishDAO();
            else if(temp.equals("dis"))
                abstractDAO = new MySQLDishDAO();

            String id = notParsedVal.substring(4, notParsedVal.length());

            int dishId = Integer.parseInt(id);

            dishesList.add((Dish) abstractDAO.findEntityById(dishId));
            System.out.println("Iterated dishes:: " + dishesList);
            i++;
            val = request.getParameter("food_item_" + i);
        }
        System.out.println(dishesList);

        FoodCalculator foodCalculator = new FoodCalculator();
        HttpSession httpSession = request.getSession();
        String username = (String)httpSession.getAttribute("LOGGED_USER");

        UserService userService = new UserService();
        User user = userService.findUserByUsername(username);
        System.out.println(foodCalculator.isWellDiet(user, dishesList));
        System.out.println(foodCalculator.getProteinsDifference());
        System.out.println(foodCalculator.getCarbohydratesDifference());
        System.out.println(foodCalculator.getFatsDifference());
        if(foodCalculator.getProteinsDifference() > 0.0){
            request.setAttribute("isWellProteins", false);
            request.setAttribute("exceededProteins", getRoundedValue(foodCalculator.getProteinsDifference()));
        } else {
            request.setAttribute("isWellProteins", true);
        }
        if(foodCalculator.getCarbohydratesDifference() > 0.0){
            request.setAttribute("isWellCarbohydrates", false);
            request.setAttribute("exceededCarbohydrates", getRoundedValue(foodCalculator.getCarbohydratesDifference()));
        } else {
            request.setAttribute("isWellCarbohydrates", true);
        }
        if(foodCalculator.getFatsDifference() > 0.0){
            request.setAttribute("isWellFats", false);
            request.setAttribute("exceededFats", getRoundedValue(foodCalculator.getFatsDifference()));
        } else {
            request.setAttribute("isWellFats", true);
        }
        request.setAttribute("isWellDiet", foodCalculator.isWellDiet(user, dishesList));

        return "/view/result.jsp";
    }

    public Double getRoundedValue(double val){
        return BigDecimal.valueOf(val).
                setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
