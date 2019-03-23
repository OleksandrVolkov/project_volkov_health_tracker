package model.services;

import model.data.services.DishTypeService;
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
public class TestDishTypeService {
    private DishTypeService dishTypeService;


    @Parameterized.Parameter
    public DishType dishType;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new DishType("NewCustomDish155"));
    }

    @Before
    public void init(){
        dishTypeService = new DishTypeService();
    }

    @Test
    public void testFindAllDishes(){
        List<DishType> dishTypes = dishTypeService.findAllDishTypes();
        List<Integer> ids = new ArrayList<>();
        for(DishType dishType: dishTypes)
            ids.add(dishType.getId());

        List<DishType> foundDishTypes = new ArrayList<>();
        for(Integer id: ids){
            DishType dishType = dishTypeService.findDishTypeById(id);
            foundDishTypes.add(dishType);
        }
        assertEquals(foundDishTypes, dishTypes);
    }

    @Test
    public void testFindDishTypeById(){
        dishTypeService.createDishType(dishType);

        DishType tempDishType = dishTypeService.findDishTypeById(dishType.getId());
        assertEquals(dishType, tempDishType);

        dishTypeService.deleteDishTypeById(dishType.getId());
    }

    @Test
    public void testCreateDish(){
        dishTypeService.createDishType(dishType);
        DishType curDishType = dishTypeService.findDishTypeById(dishType.getId());
        assertEquals(dishType, curDishType);
        dishTypeService.deleteDishTypeById(dishType.getId());
    }

    @Test
    public void testDeleteDishById(){
        dishTypeService.createDishType(dishType);
        dishTypeService.deleteDishTypeById(dishType.getId());
        assertNull(dishTypeService.findDishTypeById(dishType.getId()));
    }

    @Test
    public void testUpdateDishById(){
        dishTypeService.createDishType(dishType);
        String name = "NEW NAME!!!";
        dishType.setName(name);

        dishTypeService.updateDishTypeById(dishType, dishType.getId());
        DishType tempDishType = dishTypeService.findDishTypeById(dishType.getId());
        assertEquals(dishType, tempDishType);
        dishTypeService.deleteDishTypeById(dishType.getId());
    }
}
