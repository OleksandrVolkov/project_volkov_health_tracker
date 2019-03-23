package controller.actions.account_actions;

import controller.actions.Action;
import model.data.services.UserService;
import model.utility.MD5Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogInAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserService userService = new UserService();
        String username = request.getParameter("username");
        String passwordStr = request.getParameter("psw");
        String password = MD5Handler.md5Custom(passwordStr);
        String url = "";
        if(userService.verifyUser(username, password)){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("LOGGED_USER", username);
            System.out.println("getting user:  " + username);
            System.out.println("??????!!?!?!?!?" + httpSession.getAttribute("LOGGED_USER"));
            url = "/view/cabinet.jsp";
        } else {
            url = "/view/login_form.jsp";
            request.setAttribute("notValid",true);
        }

        return url;
    }
}
