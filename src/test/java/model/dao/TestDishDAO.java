package model.dao;

import model.data.dao.CustomDishDAO;
import model.data.dao.DishDAO;
import model.data.dao.DishTypeDAO;
import model.data.dao.UserDAO;
import model.entities.*;
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
public class TestDishDAO {
    private DishTypeDAO dishTypeDAO;
    private DishDAO dishDAO;
    private UserDAO userDAO;

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
                                new Dish("NewCustomDish112123"),
                                new Nutrients(122.1, 321.1, 432.1),
                                new DishType("newDishType11223"),
                                new User.Builder().withName("Alex").withSurname("Volkov").withEmail("email31232113@gmail.com")
                                        .withLifestyle(Lifestyle.ACTIVE).withWeight(87.0).withHeight(187.2).withPassword("root")
                                        .withUsername("my_username1132233").withSex(Sex.MALE).withAge(19).build()

                        }
                }
        );
    }


    @Before
    public void init(){
        dishTypeDAO = new DishTypeDAO();
        userDAO = new UserDAO();
        dishDAO = new DishDAO();
    }

    public void initialise(){
        userDAO.create(user);
        dishTypeDAO.create(dishType);

        dish.setNutrients(nutrients);
        dish.setDishTypeId(dishType.getId());
        dishDAO.create(dish);
    }

    public void delete(){
        userDAO.delete(user.getId());
        dishTypeDAO.delete(dishType.getId());
        dishDAO.delete(dish.getId());
    }



    @Test
    public void testCreate(){
        initialise();
        Dish curDish = dishDAO.findEntityById(dish.getId());
        assertEquals(dish, curDish);
        delete();
    }

    @Test
    public void testDelete(){
        initialise();
        delete();
        assertNull(dishDAO.findEntityById(dish.getId()));
    }

    @Test
    public void testFindAll(){
        List<Dish> dishes = dishDAO.findAll();
        List<Integer> ids = new ArrayList<>();
        for(Dish dish: dishes)
            ids.add(dish.getId());

        List<Dish> foundDishes = new ArrayList<>();
        for(Integer id: ids){
            Dish dish = dishDAO.findEntityById(id);
            foundDishes.add(dish);
        }
        assertEquals(foundDishes, dishes);
    }

    @Test
    public void testFindEntityById(){
        initialise();

        Dish tempDish = dishDAO.findEntityById(dish.getId());
        assertEquals(dish, tempDish);

        delete();
    }

    @Test
    public void testUpdate(){
        initialise();
        String name = "NEW NAME!!!";

        dish.setName(name);

        dishDAO.update(dish, dish.getId());
        Dish tempDish = dishDAO.findEntityById(dish.getId());
        assertEquals(dish, tempDish);
        delete();
    }

    @Test
    public void getLastInsertedDishes(){
        initialise();
        List<Dish> dishes = dishDAO.findAll();
        Dish tempDish = dishes.get(dishes.size() - 1);
        int id = dishDAO.getLastInsertedId();
        assertEquals(tempDish.getId(), id);
        delete();
    }
}
