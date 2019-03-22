package model.data.services;

import model.data.dao.UserDAO;
import model.entities.User;

import java.util.List;

public class UserService {
    UserDAO userDAO;

    public UserService(){
        userDAO = new UserDAO();
    }

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    public User findUserById(int id){
        return userDAO.findEntityById(id);
    }

    public boolean deleteUserById(int id){
        return userDAO.delete(id);
    }

    public boolean createUser(User user){
        return userDAO.create(user);
    }

    public User updateUserById(User user, int id){
        return userDAO.update(user, id);
    }

    public boolean verifyUser(String username, String password){
        return userDAO.verify(username, password);
    }

    public boolean isUsernameTaken(String username){
        return userDAO.isUsernameTaken(username);
    }

    public boolean isEmailTaken(String email){
        return userDAO.isEmailTaken(email);
    }

    public User findUserByUsername(String username){
        return userDAO.findUserByUsername(username);
    }
}
