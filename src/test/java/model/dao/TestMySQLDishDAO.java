package model.dao;

import model.data.dao.dao_implementations.mysql_dao.MySQLDishDAO;
import model.data.dao.dao_implementations.mysql_dao.MySQLDishTypeDAO;
import model.data.dao.dao_implementations.mysql_dao.MySQLUserDAO;
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
public class TestMySQLDishDAO {
    private MySQLDishTypeDAO mySQLDishTypeDAO;
    private MySQLDishDAO mySQLDishDAO;
    private MySQLUserDAO mySQLUserDAO;

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
        mySQLDishTypeDAO = new MySQLDishTypeDAO();
        mySQLUserDAO = new MySQLUserDAO();
        mySQLDishDAO = new MySQLDishDAO();
    }

    public void initialise(){
        mySQLUserDAO.create(user);
        mySQLDishTypeDAO.create(dishType);

        dish.setNutrients(nutrients);
        dish.setDishTypeId(dishType.getId());
        mySQLDishDAO.create(dish);
    }

    public void delete(){
        mySQLUserDAO.delete(user.getId());
        mySQLDishTypeDAO.delete(dishType.getId());
        mySQLDishDAO.delete(dish.getId());
    }



    @Test
    public void testCreate(){
        initialise();
        Dish curDish = mySQLDishDAO.findEntityById(dish.getId());
        assertEquals(dish, curDish);
        delete();
    }

    @Test
    public void testDelete(){
        initialise();
        delete();
        assertNull(mySQLDishDAO.findEntityById(dish.getId()));
    }

    @Test
    public void testFindAll(){
        List<Dish> dishes = mySQLDishDAO.findAll();
        List<Integer> ids = new ArrayList<>();
        for(Dish dish: dishes)
            ids.add(dish.getId());

        List<Dish> foundDishes = new ArrayList<>();
        for(Integer id: ids){
            Dish dish = mySQLDishDAO.findEntityById(id);
            foundDishes.add(dish);
        }
        assertEquals(foundDishes, dishes);
    }

    @Test
    public void testFindEntityById(){
        initialise();

        Dish tempDish = mySQLDishDAO.findEntityById(dish.getId());
        assertEquals(dish, tempDish);

        delete();
    }

    @Test
    public void testUpdate(){
        initialise();
        String name = "NEW NAME!!!";

        dish.setName(name);

        mySQLDishDAO.update(dish, dish.getId());
        Dish tempDish = mySQLDishDAO.findEntityById(dish.getId());
        assertEquals(dish, tempDish);
        delete();
    }

    @Test
    public void getLastInsertedDishes(){
        initialise();
        List<Dish> dishes = mySQLDishDAO.findAll();
        Dish tempDish = dishes.get(dishes.size() - 1);
        int id = mySQLDishDAO.getLastInsertedId();
        assertEquals(tempDish.getId(), id);
        delete();
    }
}
