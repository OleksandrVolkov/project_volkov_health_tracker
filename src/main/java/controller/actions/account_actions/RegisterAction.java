package controller.actions.account_actions;

import controller.actions.Action;
import model.data.services.UserService;
import model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  <h1>RegisterAction class</h1>
 *  RegisterAction is an implementation class of the Action interface.
 *  It creates a new account for a new user.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class RegisterAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserService userService = new UserService();
        User user = (User) request.getSession().getAttribute("user");
        userService.createUser(user);
        request.getSession().setAttribute("LOGGED_USER", user.getUsername());
//        String loginSession = (String) request.getSession().getAttribute("LOGGED_USER");
        return "/view/cabinet.jsp";
    }
}
