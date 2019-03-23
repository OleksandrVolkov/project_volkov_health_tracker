package model.dao;

import model.data.dao.dao_implementations.mysql_dao.MySQLDishTypeDAO;
import model.entities.DishType;
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
public class TestMySQLDishTypeDAO {
    private MySQLDishTypeDAO mySQLDishTypeDAO;


    @Parameterized.Parameter
    public DishType dishType;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new DishType("NewCustomDish152"));
    }

    @Before
    public void init(){
        mySQLDishTypeDAO = new MySQLDishTypeDAO();
    }

    @Test
    public void testFindAll(){
        List<DishType> dishTypes = mySQLDishTypeDAO.findAll();
        List<Integer> ids = new ArrayList<>();
        for(DishType dishType: dishTypes)
            ids.add(dishType.getId());

        List<DishType> foundDishTypes = new ArrayList<>();
        for(Integer id: ids){
            DishType dishType = mySQLDishTypeDAO.findEntityById(id);
            foundDishTypes.add(dishType);
        }
        assertEquals(foundDishTypes, dishTypes);
    }

    @Test
    public void testFindEntityById(){
        mySQLDishTypeDAO.create(dishType);

        DishType tempDishType = mySQLDishTypeDAO.findEntityById(dishType.getId());
        assertEquals(dishType, tempDishType);

        mySQLDishTypeDAO.delete(dishType.getId());
    }


    @Test
    public void testCreate(){
        mySQLDishTypeDAO.create(dishType);
        DishType curDishType = mySQLDishTypeDAO.findEntityById(dishType.getId());
        assertEquals(dishType, curDishType);
        mySQLDishTypeDAO.delete(dishType.getId());
    }

    @Test
    public void testDelete(){
        mySQLDishTypeDAO.create(dishType);
        mySQLDishTypeDAO.delete(dishType.getId());
        assertNull(mySQLDishTypeDAO.findEntityById(dishType.getId()));
    }

    @Test
    public void testUpdate(){
        mySQLDishTypeDAO.create(dishType);
        String name = "NEW NAME!!!";
        dishType.setName(name);

        mySQLDishTypeDAO.update(dishType, dishType.getId());
        DishType tempDishType = mySQLDishTypeDAO.findEntityById(dishType.getId());
        assertEquals(dishType, tempDishType);
        mySQLDishTypeDAO.delete(dishType.getId());
    }
}
