package model.dao;

import model.data.dao.dao_implementations.mysql_dao.MySQLCustomDishDAO;
import model.data.dao.dao_implementations.mysql_dao.MySQLDishTypeDAO;
import model.data.dao.dao_implementations.mysql_dao.MySQLUserDAO;
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
public class TestMySQLCustomMySQLDishDAO {
    private MySQLCustomDishDAO mySQLCustomDishDAO;
    private MySQLDishTypeDAO mySQLDishTypeDAO;
    private MySQLUserDAO mySQLUserDAO;

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
        mySQLCustomDishDAO = new MySQLCustomDishDAO();
        mySQLDishTypeDAO = new MySQLDishTypeDAO();
        mySQLUserDAO = new MySQLUserDAO();
    }

    public void initialise(){
        mySQLUserDAO.create(user);
        mySQLDishTypeDAO.create(dishType);

        customDish.setNutrients(nutrients);
        customDish.setUserId(user.getId());
        customDish.setDishTypeId(dishType.getId());
        mySQLCustomDishDAO.create(customDish);
    }

    public void delete(){
        mySQLUserDAO.delete(user.getId());
        mySQLDishTypeDAO.delete(dishType.getId());
        mySQLCustomDishDAO.delete(customDish.getId());
    }



    @Test
    public void testCreate(){
        initialise();
        CustomDish curCustomDish = mySQLCustomDishDAO.findEntityById(customDish.getId());
        assertEquals(customDish, curCustomDish);
        delete();
    }

    @Test
    public void testFindAll(){
        List<CustomDish> customDishes = mySQLCustomDishDAO.findAll();
        List<Integer> ids = new ArrayList<>();
        for(CustomDish customDish: customDishes)
            ids.add(customDish.getId());

        List<CustomDish> foundCustomDishes = new ArrayList<>();
        for(Integer id: ids){
            CustomDish customDish = mySQLCustomDishDAO.findEntityById(id);
            foundCustomDishes.add(customDish);
        }
        assertEquals(foundCustomDishes, customDishes);
    }

    @Test
    public void testFindEntityById(){
        initialise();

        CustomDish tempCustomDish = mySQLCustomDishDAO.findEntityById(customDish.getId());
        assertEquals(customDish, tempCustomDish);

        delete();
    }

    @Test
    public void testDelete(){
        initialise();
        delete();
        assertNull(mySQLCustomDishDAO.findEntityById(customDish.getId()));
    }

    @Test
    public void testUpdate(){
        initialise();
        String name = "NEW NAME!!!";

        customDish.setName(name);

        mySQLCustomDishDAO.update(customDish, customDish.getId());
        CustomDish tempCustomDish = mySQLCustomDishDAO.findEntityById(customDish.getId());
        assertEquals(customDish, tempCustomDish);
        delete();
    }

    //TODO:: rename ids
    @Test
    public void testGetCustomDishesByUserId(){
        initialise();
        List<CustomDish> customDishes = mySQLCustomDishDAO.getCustomDishesByUserId(user.getId());
        Integer userId = user.getId();

        List<CustomDish> allCustomDishes = mySQLCustomDishDAO.findAll();
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
        List<CustomDish> customDishes = mySQLCustomDishDAO.findAll();
        CustomDish customDishTemp = customDishes.get(customDishes.size() - 1);
        int id = mySQLCustomDishDAO.getLastInsertedCustomDishId();
        assertEquals(customDishTemp.getId(), id);

        delete();
    }
}
