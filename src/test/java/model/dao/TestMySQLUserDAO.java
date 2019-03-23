package model.dao;

import model.data.dao.dao_implementations.mysql_dao.MySQLUserDAO;
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
import static org.junit.Assert.assertTrue;
//TODO:: GET LAST INSERTED!!!!!?!?!?!?!?!??!
@RunWith(Parameterized.class)
public class TestMySQLUserDAO {
    private MySQLUserDAO mySQLUserDAO;

    @Parameterized.Parameter
    public User user;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList( new Object[][]{
                        {
                                new User.Builder().withName("Alex").withSurname("Volkov").withEmail("e176n12o16@gmail.com")
                                        .withLifestyle(Lifestyle.ACTIVE).withWeight(87.0).withHeight(187.2).withPassword("root")
                                        .withUsername("my768d12s_uen36").withSex(Sex.MALE).withAge(19).build()
                        }
                }
        );
    }

    @Before
    public void init(){
        mySQLUserDAO = new MySQLUserDAO();
    }

    @Test
    public void testFindAll(){
        List<User> users = mySQLUserDAO.findAll();
        List<Integer> ids = new ArrayList<>();
        for(User user: users)
            ids.add(user.getId());

        List<User> foundUsers = new ArrayList<>();
        for(Integer id: ids){
            User user = mySQLUserDAO.findEntityById(id);
            foundUsers.add(user);
        }
        assertEquals(foundUsers, users);
    }

    @Test
    public void testCreate(){
        mySQLUserDAO.create(user);
        System.out.println("User: " + user);
        System.out.println(user.getId());
        User curUser = mySQLUserDAO.findEntityById(user.getId());
//        System.out.println(user);
        System.out.println("Found user: " + curUser);
        assertEquals(user, curUser);
        mySQLUserDAO.delete(user.getId());
    }

    @Test
    public void testDelete(){
        mySQLUserDAO.create(user);
        mySQLUserDAO.delete(user.getId());
        assertNull(mySQLUserDAO.findEntityById(user.getId()));
    }

    @Test
    public void testVerify(){
        mySQLUserDAO.create(user);
        boolean isValidated = mySQLUserDAO.verify(user.getUsername(), user.getPassword());
        System.out.println(isValidated);
        mySQLUserDAO.delete(user.getId());
        assertTrue(isValidated);
    }

    @Test
    public void findUserByUsername(){
        mySQLUserDAO.create(user);
        String username = user.getUsername();
        User tempUser = mySQLUserDAO.findUserByUsername(username);
        mySQLUserDAO.delete(user.getId());
        assertEquals(user, tempUser);
    }

    //TODO: PASSWORD!!!
    @Test
    public void testUpdate(){
        String curEmail = "val89689@gmail.com";
        mySQLUserDAO.create(user);
        user.setEmail(curEmail);

        mySQLUserDAO.update(user, user.getId());
        User userTemp = mySQLUserDAO.findEntityById(user.getId());
        assertEquals(user, userTemp);
        mySQLUserDAO.delete(user.getId());
    }

    @Test
    public void testFindEntityById(){
        mySQLUserDAO.create(user);
        User tempUser = mySQLUserDAO.findEntityById(user.getId());
        assertEquals(user, tempUser);

        mySQLUserDAO.delete(user.getId());
    }

    @Test
    public void testIsEmailTaken(){
        mySQLUserDAO.create(user);
        boolean isEmailTaken = mySQLUserDAO.isEmailTaken(user.getEmail());
        mySQLUserDAO.delete(user.getId());
        assertTrue(isEmailTaken);
    }

    @Test
    public void testIsUsernameTaken(){
        mySQLUserDAO.create(user);
        boolean isUsernameTaken = mySQLUserDAO.isUsernameTaken(user.getUsername());
        mySQLUserDAO.delete(user.getId());
        assertTrue(isUsernameTaken);
    }

    @Test
    public void testGetUserIndexByEmail(){
        mySQLUserDAO.create(user);
        Integer userId = mySQLUserDAO.getLastInsertedUserId();
        assertEquals(user.getId(), userId);
        mySQLUserDAO.delete(user.getId());
    }
}
