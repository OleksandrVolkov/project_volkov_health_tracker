package controller.actions.body_actions;

import controller.actions.Action;
import controller.utility.LanguageHandler;
import model.data.dao.dao_implementations.mysql_dao.MySQLUserDAO;
import model.entities.User;
import model.entities.enums.Language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 *  <h1>ShowInfoAboutYourselfAction class</h1>
 *  ShowInfoAboutYourselfAction is an implementation class of the Action interface.
 *  It shows information about the user.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class ShowInfoAboutYourselfAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = "/view/about_yourself.jsp";

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

//        String lang = request.getParameter("lang");
//        if(lang != null && !lang.equals("")){
//            request.getServletContext().setAttribute("lang", lang);
//        } else {
//            request.getServletContext().setAttribute("lang", "en");
//            lang = "en";
//        }
//        System.out.println("LANG:::: ::  :  " + lang);
        String lang = request.getParameter("lang");
        if(lang != null && !lang.equals("")){
            System.out.println("NOT EQULAS TO NULL: " + lang);
            request.getServletContext().setAttribute("lang", lang);
        } else {
            String val = (String)request.getServletContext().getAttribute("lang");
            if(val != null){
                request.getServletContext().setAttribute("lang", val);
                System.out.println(val + " AAAAA");
                lang = val;
            }else {
                System.out.println("EQUALS TO NULL: " + lang);
                request.getServletContext().setAttribute("lang", "en");
                lang = "en";
            }
        }

        Map<String, String> authForm = LanguageHandler.getHashMapOfValuesByPageUrl(url, Language.getLanguage(lang));
        authForm.put("lang", lang);
        System.out.println(authForm);

        request.setAttribute("language", authForm);

        return "/view/about_yourself.jsp";
    }
}