package controller.actions.account_actions;

import controller.actions.Action;

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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute("LOGGED_USER", null);
        return "/view/login_form.jsp";
    }
}
