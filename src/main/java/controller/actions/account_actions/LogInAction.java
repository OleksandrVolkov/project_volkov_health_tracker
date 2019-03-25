package controller.actions.account_actions;

import controller.actions.Action;
import model.data.services.UserService;
import model.utility.MD5Handler;

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
//            url = "/view/cabinet.jsp";
            url = "/load_data?action=load_cabinet";
        } else {
            request.setAttribute("notValid",true);
            url = "/load_data?action=load_login";
        }

        return url;
    }
}
