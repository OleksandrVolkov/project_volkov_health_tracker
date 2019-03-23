package model.services;


import model.data.dao.CustomDishDAO;
import model.data.dao.DishTypeDAO;
import model.data.dao.UserDAO;
import model.data.services.CustomDishService;
import model.data.services.DishTypeService;
import model.data.services.UserService;
import model.entities.CustomDish;
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
public class TestCustomDishService {
    private CustomDishService customDishService;
    private DishTypeService dishTypeService;
    private UserService userService;

    @Parameterized.Parameter
    public CustomDish customDish;

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
                                new CustomDish("NewCustomDish6"),
                                new Nutrients(122.1, 321.1, 432.1),
                                new DishType("newDishType9"),
                                new User.Builder().withName("Alex").withSurname("Volkov").withEmail("email152@gmail.com")
                                        .withLifestyle(Lifestyle.ACTIVE).withWeight(87.0).withHeight(187.2).withPassword("root")
                                        .withUsername("my_username1252").withSex(Sex.MALE).withAge(19).build()

                        }
                }
        );
    }


    @Before
    public void init(){
        customDishService = new CustomDishService();
        dishTypeService = new DishTypeService();
        userService = new UserService();
    }


    public void initialise(){
        System.out.println(user);
        userService.createUser(user);
        System.out.println(user);
        dishTypeService.createDishType(dishType);

        customDish.setNutrients(nutrients);
        customDish.setUserId(user.getId());
        customDish.setDishTypeId(dishType.getId());
        customDishService.createCustomDish(customDish);
    }

    public void delete(){
        userService.deleteUserById(user.getId());
        dishTypeService.deleteDishTypeById(dishType.getId());
        customDishService.deleteCustomDishById(customDish.getId());
    }

    @Test
    public void testFindAllCustomDishes(){
        List<CustomDish> customDishes = customDishService.findAllCustomDishes();
        List<Integer> ids = new ArrayList<>();
        for(CustomDish customDish: customDishes)
            ids.add(customDish.getId());

        List<CustomDish> foundCustomDishes = new ArrayList<>();
        for(Integer id: ids){
            CustomDish customDish = customDishService.findCustomDishById(id);
            foundCustomDishes.add(customDish);
        }
        assertEquals(foundCustomDishes, customDishes);
    }

    @Test
    public void testCreate(){
        initialise();
        CustomDish curCustomDish = customDishService.findCustomDishById(customDish.getId());
        assertEquals(customDish, curCustomDish);
        delete();
    }

    @Test
    public void testFindEntityById(){
        initialise();

        CustomDish tempCustomDish = customDishService.findCustomDishById(customDish.getId());
        assertEquals(customDish, tempCustomDish);

        delete();
    }

    @Test
    public void testDeleteCustomDishById(){
        initialise();
        delete();
        assertNull(customDishService.findCustomDishById(customDish.getId()));
    }

    @Test
    public void testUpdateCustomDishById(){
        initialise();
        String name = "NEW NAME!!!";

        customDish.setName(name);

        customDishService.updateCustomDishById(customDish, customDish.getId());
        CustomDish tempCustomDish = customDishService.findCustomDishById(customDish.getId());
        assertEquals(customDish, tempCustomDish);
        delete();
    }

    @Test
    public void testGetCustomDishesByUserId(){
        initialise();
        initialise();
        List<CustomDish> customDishes = customDishService.getCustomDishesByUserId(user.getId());
        Integer userId = user.getId();

        List<CustomDish> allCustomDishes = customDishService.findAllCustomDishes();
        List<CustomDish> finalCustomDishes = new ArrayList<>();
        for(CustomDish customDish: allCustomDishes)
            if(customDish.getUserId() == userId)
                finalCustomDishes.add(customDish);

        assertEquals(customDishes, finalCustomDishes);
        delete();
        delete();
    }
}
