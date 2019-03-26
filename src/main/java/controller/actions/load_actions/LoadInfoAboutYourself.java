package controller.actions.load_actions;

import controller.actions.Action;
import controller.utility.LanguageHandler;
import model.data.services.UserService;
import model.entities.User;
import model.entities.enums.Language;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LoadInfoAboutYourself implements Action{
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(LoadDishesToCheck.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Loading information about yourself");
        String url = "/view/about_yourself.jsp";

        log.trace("Getting username from the session");
        HttpSession httpSession = request.getSession();
        String username = (String)httpSession.getAttribute("LOGGED_USER");
        UserService userService = new UserService();
        log.trace("Getting user by username");
        User user = userService.findUserByUsername(username);

        log.trace("Setting attribute values");
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

        log.trace("Getting language value");
        LanguageHandler languageHandler = new LanguageHandler();
        String lang = languageHandler.getLangValue(request, request.getParameter("lang"));

        Map<String, String> authForm = LanguageHandler.getHashMapOfValuesByPageUrl(url, Language.getLanguage(lang));
        authForm.put("lang", lang);

        request.setAttribute("language", authForm);

//        return "/body?action=update_user_data";
        log.trace("Returning url");
        return "/view/about_yourself.jsp";
    }
}
