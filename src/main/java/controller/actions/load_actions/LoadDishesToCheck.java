package controller.actions.load_actions;

import controller.actions.Action;
import model.data.dao.dao_implementations.mysql_dao.MySQLDishTypeDAO;
import model.data.services.CustomDishService;
import model.data.services.DishService;
import model.data.services.UserService;
import model.entities.CustomDish;
import model.entities.Dish;
import model.entities.DishType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LoadDishesToCheck implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DishService dishService = new DishService();
        CustomDishService customDishService = new CustomDishService();
        UserService userService = new UserService();

        List<Dish> dishes = dishService.findAllDishes();
        System.out.println("Dishes before: " + dishes);
        HttpSession httpSession = request.getSession();
        String username = (String)httpSession.getAttribute("LOGGED_USER");
        //TODO: REMAKE!!!
        List<CustomDish> customDishes = customDishService.getCustomDishesByUserId(userService.findUserByUsername(username).getId());
        System.out.println("Custom dishes: " + customDishes);
        dishes.addAll(customDishes);
        System.out.println("Dishes after: " + dishes);
//        dishes.sort(new DishTypeComparator());


        Map<Dish, String> dishesMap = new TreeMap<>();
        MySQLDishTypeDAO mySQLDishTypeDAO = new MySQLDishTypeDAO();


        for(Dish dish: dishes){
            DishType dishType = mySQLDishTypeDAO.findEntityById(dish.getDishTypeId());
            System.out.println(dish + "  ........   " + dishType);
            dishesMap.put(dish, dishType.getName());
        }

        System.out.println(dishesMap);

        request.setAttribute("dishes", dishesMap);

        return "view/check_diet.jsp";
    }
}
