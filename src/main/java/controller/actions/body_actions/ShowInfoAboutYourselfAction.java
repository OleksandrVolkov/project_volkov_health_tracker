package controller.actions.body_actions;

import controller.actions.Action;
import model.data.dao.dao_implementations.mysql_dao.MySQLUserDAO;
import model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowInfoAboutYourselfAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession httpSession = request.getSession();
        String username = (String)httpSession.getAttribute("LOGGED_USER");
        System.out.println("USERNAME  :::  " + username);
        MySQLUserDAO mySQLUserDAO = new MySQLUserDAO();
        User user = mySQLUserDAO.findUserByUsername(username);


        request.setAttribute("id", user.getId());
        request.setAttribute("name", user.getName());
        request.setAttribute("surname", user.getSurname());
        request.setAttribute("username", user.getUsername());
        request.setAttribute("age", user.getAge());
        request.setAttribute("height", user.getHeight());
        request.setAttribute("weight", user.getWeight());
        request.setAttribute("lifestyle", user.getLifestyle());
        request.setAttribute("password", user.getPassword());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("sex", user.getSex());

        return "/view/about_yourself.jsp";
    }
}
