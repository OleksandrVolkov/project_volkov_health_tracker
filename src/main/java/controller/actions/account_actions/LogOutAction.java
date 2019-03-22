package controller.actions.account_actions;

import controller.actions.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute("LOGGED_USER", null);
        return "/view/login_form.jsp";
    }
}
