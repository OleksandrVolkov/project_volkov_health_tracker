package controller.utility;

import model.entities.Dish;
import model.entities.User;
import model.entities.enums.Lifestyle;
import model.entities.enums.Sex;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        User user = new User();
        user.setLifestyle(Lifestyle.MODERATE);
        user.setAge(48);
        user.setWeight(69.0);
        user.setHeight(171.0);
        user.setSex(Sex.MALE);
        FoodCalculator foodCalculator = new FoodCalculator();
//        System.out.println(foodCalculator.neededCallories(user));
//        System.out.println(foodCalculator.getNeededNutrients(foodCalculator.neededCallories(user)));
        System.out.println(foodCalculator.neededCallories(user));
    }
}
