package controller.actions.load_actions;

import controller.actions.Action;
import controller.utility.LanguageHandler;
import model.data.services.UserService;
import model.entities.User;
import model.entities.enums.Language;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoadRegistrationPropertiesAction implements Action{
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(LoadRegistrationPropertiesAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.trace("Loading registration properties");
        String url = "/view/registration_form.jsp";

        log.trace("Setting attributes");
                    request.setAttribute("isValidEmail", true);
                    request.setAttribute("isValidName", true);
                    request.setAttribute("isValidUsername", true);
                    request.setAttribute("isValidSurname", true);
                    request.setAttribute("isValidPassword", true);
                    request.setAttribute("isValidHeight", true);
                    request.setAttribute("isValidWeight", true);
                    request.setAttribute("isValidAge", true);
                    request.setAttribute("isNotTakenEmail", true);
                    request.setAttribute("isNotTakenUsername", true);

        log.trace("Getting language value");
        LanguageHandler languageHandler = new LanguageHandler();
        String lang = languageHandler.getLangValue(request, request.getParameter("lang"));

        log.trace("Getting registration properties");
        Map<String, String> regProps = LanguageHandler.getHashMapOfValuesByPageUrl(url, Language.getLanguage(lang));
        request.setAttribute("language", regProps);

        log.trace("Returning url");
        return url;
    }
}
