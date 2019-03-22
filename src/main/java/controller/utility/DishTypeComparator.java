package controller.utility;

import model.entities.Dish;

import java.util.Comparator;

public class DishTypeComparator implements Comparator<Dish>{
    @Override
    public int compare(Dish dish1, Dish dish2) {
        return dish1.getName().compareTo(dish2.getName());
    }
}
