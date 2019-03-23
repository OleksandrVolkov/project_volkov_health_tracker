package model.dao;

import model.data.dao.CustomDishDAO;
import model.data.dao.DishTypeDAO;
import model.data.dao.UserDAO;
import model.data.dao.connection.ConnectionManager;
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
public class TestCustomDishDAO {
    private CustomDishDAO customDishDAO;
    private DishTypeDAO dishTypeDAO;
    private UserDAO userDAO;

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
                            new CustomDish("NewCustomDish19"),
                            new Nutrients(122.1, 321.1, 432.1),
                            new DishType("newDishType19"),
                            new User.Builder().withName("Alex").withSurname("Volkov").withEmail("email13221@gmail.com")
                                        .withLifestyle(Lifestyle.ACTIVE).withWeight(87.0).withHeight(187.2).withPassword("root")
                                        .withUsername("my_username1231").withSex(Sex.MALE).withAge(19).build()

                        }
                }
        );
    }


    @Before
    public void init(){
        customDishDAO = new CustomDishDAO();
        dishTypeDAO = new DishTypeDAO();
        userDAO = new UserDAO();
    }

    public void initialise(){
        userDAO.create(user);
        dishTypeDAO.create(dishType);

        customDish.setNutrients(nutrients);
        customDish.setUserId(user.getId());
        customDish.setDishTypeId(dishType.getId());
        customDishDAO.create(customDish);
    }

    public void delete(){
        userDAO.delete(user.getId());
        dishTypeDAO.delete(dishType.getId());
        customDishDAO.delete(customDish.getId());
    }



    @Test
    public void testCreate(){
        initialise();
        CustomDish curCustomDish = customDishDAO.findEntityById(customDish.getId());
        assertEquals(customDish, curCustomDish);
        delete();
    }

    @Test
    public void testFindAll(){
        List<CustomDish> customDishes = customDishDAO.findAll();
        List<Integer> ids = new ArrayList<>();
        for(CustomDish customDish: customDishes)
            ids.add(customDish.getId());

        List<CustomDish> foundCustomDishes = new ArrayList<>();
        for(Integer id: ids){
            CustomDish customDish = customDishDAO.findEntityById(id);
            foundCustomDishes.add(customDish);
        }
        assertEquals(foundCustomDishes, customDishes);
    }

    @Test
    public void testFindEntityById(){
        initialise();

        CustomDish tempCustomDish = customDishDAO.findEntityById(customDish.getId());
        assertEquals(customDish, tempCustomDish);

        delete();
    }

    @Test
    public void testDelete(){
        initialise();
        delete();
        assertNull(customDishDAO.findEntityById(customDish.getId()));
    }

    @Test
    public void testUpdate(){
        initialise();
        String name = "NEW NAME!!!";

        customDish.setName(name);

        customDishDAO.update(customDish, customDish.getId());
        CustomDish tempCustomDish = customDishDAO.findEntityById(customDish.getId());
        assertEquals(customDish, tempCustomDish);
        delete();
    }

    //TODO:: rename ids
    @Test
    public void testGetCustomDishesByUserId(){
        initialise();
        List<CustomDish> customDishes = customDishDAO.getCustomDishesByUserId(user.getId());
        Integer userId = user.getId();

        List<CustomDish> allCustomDishes = customDishDAO.findAll();
        List<CustomDish> finalCustomDishes = new ArrayList<>();
        for(CustomDish customDish: allCustomDishes)
            if(customDish.getUserId() == userId)
                finalCustomDishes.add(customDish);

        assertEquals(customDishes, finalCustomDishes);
        delete();
    }

    @Test
    public void getLastInsertedCustomDishId(){
        initialise();
        List<CustomDish> customDishes = customDishDAO.findAll();
        CustomDish customDishTemp = customDishes.get(customDishes.size() - 1);
        int id = customDishDAO.getLastInsertedCustomDishId();
        assertEquals(customDishTemp.getId(), id);

        delete();
    }
}
