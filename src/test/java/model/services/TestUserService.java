package model.services;

import model.data.services.UserService;
import model.entities.User;
import model.entities.enums.Lifestyle;
import model.entities.enums.Sex;
import org.junit.Before;
import org.junit.Ignore;
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

@RunWith(Parameterized.class)
public class TestUserService {
    private UserService userService;

    @Parameterized.Parameter
    public User user;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList( new Object[][]{
                        {
                                new User.Builder().withName("Alex").withSurname("Volkov").withEmail("neweml12321@gmail.com")
                                        .withLifestyle(Lifestyle.ACTIVE).withWeight(87.0).withHeight(187.2).withPassword("root")
                                        .withUsername("new_usernam13214").withSex(Sex.MALE).withAge(19).build()
                        }
                }
        );
    }

    @Before
    public void init(){
        userService = new UserService();
    }


    @Test
    public void testFindAllUsers(){
        List<User> users = userService.findAllUsers();
        System.out.println(users);
        List<Integer> ids = new ArrayList<>();
        for(User user: users)
            ids.add(user.getId());
        List<User> foundUsers = new ArrayList<>();
        for(Integer id: ids){
            User user = userService.findUserById(id);
            foundUsers.add(user);
        }
        assertEquals(foundUsers, users);
    }

    @Test
    public void testCreateUser(){
        userService.createUser(user);
        User curUser = userService.findUserById(user.getId());
        assertEquals(user, curUser);
        userService.deleteUserById(user.getId());
    }

    @Test
    public void testDeleteUserById(){
        userService.createUser(user);
        userService.deleteUserById(user.getId());
        assertNull(userService.findUserById(user.getId()));
    }

    @Test
    public void testVerify(){
        userService.createUser(user);
        boolean isValidated = userService.verifyUser(user.getUsername(), user.getPassword());
        userService.deleteUserById(user.getId());
        assertTrue(isValidated);
    }

    @Test
    public void findUserByUsername(){
        userService.createUser(user);
        String username = user.getUsername();
        User tempUser = userService.findUserByUsername(username);
        userService.deleteUserById(user.getId());
        assertEquals(user, tempUser);
    }

    @Ignore
    public void testUpdate(){
        String curEmail = "val@gmail.com";

        userService.createUser(user);
        user.setEmail(curEmail);
        userService.updateUserById(user, user.getId());
        User userTemp = userService.findUserById(user.getId());
        assertEquals(user, userTemp);
        userService.deleteUserById(user.getId());
    }

}
