package controller.utility;

import model.entities.Dish;
import model.entities.Nutrients;
import model.entities.User;
import model.entities.enums.coeffitients_harris_bendict.CoeffitientsHarrisBendictFactory;
import model.entities.enums.coeffitients_harris_bendict.CoeffitientsHarrisBenedict;

import java.util.List;

public class FoodCalculator {
    public boolean isWellDiet(User user, List<Dish> dishes){
        double callories = neededCallories(user);
        Nutrients neededNutrients = getNeededNutrients(callories);
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        System.out.println("DISHES::::::::::::::::    " + dishes);
        for (Dish dish: dishes){
            proteins += dish.getNutrients().getProteins();
            fats += dish.getNutrients().getFats();
            carbohydrates += dish.getNutrients().getCarbohydrates();
        }

        if(proteins > neededNutrients.getProteins()
                || carbohydrates > neededNutrients.getCarbohydrates()
                || fats > neededNutrients.getFats()) {
            System.out.println("Too much of the nutrients! ");
            return false;
        }
        return true;
    }

    public double neededCallories(User user){
        double bmr = getBMR(user);
        double amr = getAMR(user);
        return amr * bmr;
    }
    public Nutrients getNeededNutrients(double calories){
        double proteins = (calories * 0.4) / 4;
        double fats = (calories * 0.2) / 9;
        double carbohydrates = (calories * 0.4) / 4;
        Nutrients nutrients = new Nutrients(proteins, carbohydrates, fats);
        return nutrients;
    }

    public double getBMR(User user){
        CoeffitientsHarrisBenedict coeffitients = CoeffitientsHarrisBendictFactory.getInstanceBySex(user.getSex());
        double bmr = coeffitients.getInitialCoefficient() +
                (coeffitients.getWeightCoefficient() * user.getWeight()) +
                (coeffitients.getHeightCoefficient() * user.getHeight()) -
                (coeffitients.getAgeCoefficient() * user.getAge());
        return bmr;
    }

    public double getAMR(User user){
        return user.getLifestyle().getAMR();
    }
}
