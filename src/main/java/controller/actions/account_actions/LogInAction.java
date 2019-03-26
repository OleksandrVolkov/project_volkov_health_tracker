package controller.actions.account_actions;

import controller.actions.Action;
import model.data.services.UserService;
import controller.utility.MD5Handler;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  <h1>LogInAction class</h1>
 *  LogInAction is an implementation class of the Action interface.
 *  It logs user into the his/her personal cabinet.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class LogInAction implements Action{
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(LogInAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Login in an account");
        log.trace("Getting parameters from request");
        UserService userService = new UserService();
        String username = request.getParameter("username");
        String passwordStr = request.getParameter("psw");
        String password = MD5Handler.md5Custom(passwordStr);
        String url = "";
        log.trace("Verifying user");
        if(userService.verifyUser(username, password)){
            log.trace("User is verified");
            HttpSession httpSession = request.getSession();
            log.trace("Setting LOGGED_USER value: " + username);
            httpSession.setAttribute("LOGGED_USER", username);
            url = "/load_data?action=load_cabinet";
        } else {
            log.trace("User is not verified");
            request.setAttribute("notValid",true);
            url = "/load_data?action=load_login";
        }
        log.trace("Returning utl: " + url);

        return url;
    }
}
