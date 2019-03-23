package model.data.dao.dao_interfaces;

import model.entities.User;

public interface UserDAO {
    boolean verify(String username, String password);
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
    User findUserByUsername(String username);
    Integer getLastInsertedUserId();
}
