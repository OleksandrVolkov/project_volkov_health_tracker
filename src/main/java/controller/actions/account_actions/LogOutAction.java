package controller.actions.account_actions;

import controller.actions.Action;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *  <h1>LogOutAction class</h1>
 *  LogOutAction is an implementation class of the Action interface.
 *  It logs user out his/her account.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class LogOutAction implements Action {
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(LogOutAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Loging out an account");
        log.trace("Getting session attributes");
        request.getSession().setAttribute("LOGGED_USER", null);
        log.trace("Returning url");
        return "/load_data?action=load_login";
    }
}
