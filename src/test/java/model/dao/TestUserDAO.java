package model.dao;

import model.data.dao.UserDAO;
import model.data.dao.connection.ConnectionManager;
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
public class TestUserDAO {
    private UserDAO userDAO;

    @Parameterized.Parameter
    public User user;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList( new Object[][]{
                        {
                                new User.Builder().withName("Alex").withSurname("Volkov").withEmail("email1211136@gmail.com")
                                        .withLifestyle(Lifestyle.ACTIVE).withWeight(87.0).withHeight(187.2).withPassword("root")
                                        .withUsername("my_username1211136").withSex(Sex.MALE).withAge(19).build()
                        }
                }
        );
    }

    @Before
    public void init(){
        userDAO = new UserDAO();
    }

    @Test
    public void testFindAll(){
        List<User> users = userDAO.findAll();
        List<Integer> ids = new ArrayList<>();
        for(User user: users)
            ids.add(user.getId());

        List<User> foundUsers = new ArrayList<>();
        for(Integer id: ids){
            User user = userDAO.findEntityById(id);
            foundUsers.add(user);
        }
        assertEquals(foundUsers, users);
    }

    @Test
    public void testCreate(){
        userDAO.create(user);
        System.out.println(user.getId());
        User curUser = userDAO.findEntityById(user.getId());
        assertEquals(user, curUser);
        userDAO.delete(user.getId());
    }

    @Test
    public void testDelete(){
        userDAO.create(user);
        userDAO.delete(user.getId());
        assertNull(userDAO.findEntityById(user.getId()));
    }

    @Test
    public void testVerify(){
        userDAO.create(user);
        boolean isValidated = userDAO.verify(user.getUsername(), user.getPassword());
        System.out.println(isValidated);
        userDAO.delete(user.getId());
        assertTrue(isValidated);
    }

    @Test
    public void findUserByUsername(){
        userDAO.create(user);
        String username = user.getUsername();
        User tempUser = userDAO.findUserByUsername(username);
        userDAO.delete(user.getId());
        assertEquals(user, tempUser);
    }

    @Test
    public void testUpdate(){
        String curEmail = "val@gmail.com";
        String curPass = "poajsd";
        userDAO.create(user);
        user.setEmail(curEmail);
        user.setPassword(curPass);
        userDAO.update(user, user.getId());
        User userTemp = userDAO.findEntityById(user.getId());
        assertEquals(user, userTemp);
        userDAO.delete(user.getId());
    }

    @Test
    public void testFindEntityById(){
        userDAO.create(user);
        User tempUser = userDAO.findEntityById(user.getId());
        assertEquals(user, tempUser);

        userDAO.delete(user.getId());
    }

    @Test
    public void testIsEmailTaken(){
        userDAO.create(user);
        boolean isEmailTaken = userDAO.isEmailTaken(user.getEmail());
        userDAO.delete(user.getId());
        assertTrue(isEmailTaken);
    }

    @Test
    public void testIsUsernameTaken(){
        userDAO.create(user);
        boolean isUsernameTaken = userDAO.isUsernameTaken(user.getUsername());
        userDAO.delete(user.getId());
        assertTrue(isUsernameTaken);
    }

    @Test
    public void testGetUserIndexByEmail(){
        userDAO.create(user);
        Integer userId = userDAO.getLastInsertedUserId();
        assertEquals(user.getId(), userId);
        userDAO.delete(user.getId());
    }
}
