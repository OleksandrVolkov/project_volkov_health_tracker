package model.dao;

import model.data.dao.DishTypeDAO;
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
public class TestDishTypeDAO {
    private DishTypeDAO dishTypeDAO;


    @Parameterized.Parameter
    public DishType dishType;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new DishType("NewCustomDish152"));
    }

    @Before
    public void init(){
        dishTypeDAO = new DishTypeDAO();
    }

    @Test
    public void testFindAll(){
        List<DishType> dishTypes = dishTypeDAO.findAll();
        List<Integer> ids = new ArrayList<>();
        for(DishType dishType: dishTypes)
            ids.add(dishType.getId());

        List<DishType> foundDishTypes = new ArrayList<>();
        for(Integer id: ids){
            DishType dishType = dishTypeDAO.findEntityById(id);
            foundDishTypes.add(dishType);
        }
        assertEquals(foundDishTypes, dishTypes);
    }

    @Test
    public void testFindEntityById(){
        dishTypeDAO.create(dishType);

        DishType tempDishType = dishTypeDAO.findEntityById(dishType.getId());
        assertEquals(dishType, tempDishType);

        dishTypeDAO.delete(dishType.getId());
    }


    @Test
    public void testCreate(){
        dishTypeDAO.create(dishType);
        DishType curDishType = dishTypeDAO.findEntityById(dishType.getId());
        assertEquals(dishType, curDishType);
        dishTypeDAO.delete(dishType.getId());
    }

    @Test
    public void testDelete(){
        dishTypeDAO.create(dishType);
        dishTypeDAO.delete(dishType.getId());
        assertNull(dishTypeDAO.findEntityById(dishType.getId()));
    }

    @Test
    public void testUpdate(){
        dishTypeDAO.create(dishType);
        String name = "NEW NAME!!!";
        dishType.setName(name);

        dishTypeDAO.update(dishType, dishType.getId());
        DishType tempDishType = dishTypeDAO.findEntityById(dishType.getId());
        assertEquals(dishType, tempDishType);
        dishTypeDAO.delete(dishType.getId());
    }
}
