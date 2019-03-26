package controller.actions.account_actions;

import controller.actions.Action;
import model.data.services.UserService;
import model.entities.User;
import org.apache.log4j.Logger;

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
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(RegisterAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Registering an account");
        UserService userService = new UserService();
        log.trace("Getting user as the request attribute");
        User user = (User) request.getSession().getAttribute("user");
        log.trace("Creating user");
        userService.createUser(user);
        log.trace("Setting attribute LOGGED_USER");
        request.getSession().setAttribute("LOGGED_USER", user.getUsername());
        log.trace("Returning url");
        return "/load_data?action=load_cabinet";
    }
}
