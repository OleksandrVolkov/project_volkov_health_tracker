package controller.actions.body_actions;

import controller.actions.Action;
import controller.utility.FoodCalculator;
import model.data.dao.AbstractDAO;
import model.data.dao.CustomDishDAO;
import model.data.dao.DishDAO;
import model.data.dao.UserDAO;
import model.entities.Dish;
import model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CheckDietAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int i = 2;
        DishDAO dishDAO = new DishDAO();
        List<Dish> dishesList = new ArrayList<>();

        String val = request.getParameter("food_item_" + i);
        while(val != null){
            String notParsedVal = request.getParameter("food_item_" + i);

            String temp = notParsedVal.substring(0, 3);
            System.out.println(temp+" TEMP_____________________");
            AbstractDAO abstractDAO = null;
            if(temp.equals("cus"))
                abstractDAO = new CustomDishDAO();
            else if(temp.equals("dis"))
                abstractDAO = new DishDAO();
            String id = notParsedVal.substring(4, notParsedVal.length());

            int dishId = Integer.parseInt(id);

            System.out.println("Dish id :::  " + dishId);
            System.out.println("Find entity by id " + dishId + " = " + abstractDAO.findEntityById(dishId));
            dishesList.add((Dish) abstractDAO.findEntityById(dishId));
            System.out.println("Iterated dishes:: " + dishesList);
            i++;
            val = request.getParameter("food_item_" + i);
        }
        System.out.println(dishesList);

        FoodCalculator foodCalculator = new FoodCalculator();
        HttpSession httpSession = request.getSession();
        String username = (String)httpSession.getAttribute("LOGGED_USER");
        System.out.println("USERNAME  :::  " + username);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUserByUsername(username);
        System.out.println("!!!!!!!!!!" + user);
        System.out.println("THE DIET IS GOOD ::  " + foodCalculator.isWellDiet(user, dishesList));

//        String str = "Your diet is absolutely right! Keep eating in this way! Total calories:";


        return "...";
    }
}
