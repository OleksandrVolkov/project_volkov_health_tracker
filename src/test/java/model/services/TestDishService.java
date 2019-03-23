package model.services;

import model.data.services.DishService;
import model.data.services.DishTypeService;
import model.data.services.UserService;
import model.entities.Dish;
import model.entities.DishType;
import model.entities.Nutrients;
import model.entities.User;
import model.entities.enums.Lifestyle;
import model.entities.enums.Sex;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Parameterized.class)
public class TestDishService {
    private DishTypeService dishTypeService;
    private DishService dishService;
    private UserService userService;

    @Parameterized.Parameter
    public Dish dish;

    @Parameterized.Parameter(1)
    public Nutrients nutrients;

    @Parameterized.Parameter(2)
    public DishType dishType;

    @Parameterized.Parameter(3)
    public User user;



    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList( new Object[][]{
                        {
                                new Dish("NewCustomDish1189"),
                                new Nutrients(122.1, 321.1, 432.1),
                                new DishType("newDishType1189"),
                                new User.Builder().withName("Alex").withSurname("Volkov").withEmail("email159@gmail.com")
                                        .withLifestyle(Lifestyle.ACTIVE).withWeight(87.0).withHeight(187.2).withPassword("root")
                                        .withUsername("my_username159").withSex(Sex.MALE).withAge(19).build()

                        }
                }
        );
    }


    @Before
    public void init(){
        dishTypeService = new DishTypeService();
        userService = new UserService();
        dishService = new DishService();
    }

    public void initialise(){
        userService.createUser(user);
        dishTypeService.createDishType(dishType);

        dish.setNutrients(nutrients);
        dish.setDishTypeId(dishType.getId());
        dishService.createDish(dish);
    }

    public void delete(){
        userService.deleteUserById(user.getId());
        dishTypeService.deleteDishTypeById(dishType.getId());
        dishService.deleteDishById(dish.getId());
    }


    @Test
    public void testFindAllDishes(){
        List<Dish> dishes = dishService.findAllDishes();
        List<Integer> ids = new ArrayList<>();
        for(Dish dish: dishes)
            ids.add(dish.getId());

        List<Dish> foundDishes = new ArrayList<>();
        for(Integer id: ids){
            Dish dish = dishService.findDishById(id);
            foundDishes.add(dish);
        }
        assertEquals(foundDishes, dishes);
    }

    @Test
    public void testFindDishById(){
        initialise();
        Dish tempDish = dishService.findDishById(dish.getId());
        assertEquals(dish, tempDish);
        delete();
    }

    @Test
    public void testDeleteDishById(){
        initialise();
        delete();
        assertNull(dishService.findDishById(dish.getId()));
    }

    @Test
    public void testCreateDish(){
        initialise();
        Dish curDish = dishService.findDishById(dish.getId());
        assertEquals(dish, curDish);
        delete();
    }

    @Test
    public void testUpdateDishById(){
        initialise();
        String name = "NEW NAME!!!";

        dish.setName(name);

        dishService.updateDishById(dish, dish.getId());
        Dish tempDish = dishService.findDishById(dish.getId());
        assertEquals(dish, tempDish);
        delete();
    }
}
