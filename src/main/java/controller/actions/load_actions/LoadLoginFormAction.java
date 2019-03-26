package controller.actions.load_actions;

import controller.actions.Action;
import controller.utility.LanguageHandler;
import model.entities.enums.Language;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoadLoginFormAction implements Action {
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(LoadLoginFormAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Loading login form action");
        String url = "/view/login_form.jsp";

        log.trace("Getting language value");
        LanguageHandler languageHandler = new LanguageHandler();
        String lang = languageHandler.getLangValue(request, request.getParameter("lang"));

        log.trace("Getting login form map");
        Map<String, String> loginMap = LanguageHandler.getHashMapOfValuesByPageUrl(url, Language.getLanguage(lang));
//        login.put("lang", lang);
        request.setAttribute("language", loginMap);
        log.trace("Returning url");
        return "/view/login_form.jsp";
    }
}
