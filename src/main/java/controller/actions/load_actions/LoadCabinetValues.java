package controller.actions.load_actions;

import controller.actions.Action;
import controller.actions.account_actions.ValidateFormAction;
import controller.utility.LanguageHandler;
import model.entities.enums.Language;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoadCabinetValues implements Action{
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(ValidateFormAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Loading cabinet values");
        String url = "/view/cabinet.jsp";
        LanguageHandler languageHandler = new LanguageHandler();
        log.trace("Getting lang value");
        String lang = languageHandler.getLangValue(request, request.getParameter("lang"));

        log.trace("Getting authForm");
        Map<String, String> cabinetMap = LanguageHandler.getHashMapOfValuesByPageUrl(url, Language.getLanguage(lang));
//        authForm.put("lang", lang);

        request.setAttribute("language", cabinetMap);
        log.trace("Returning url: " + url);

        return url;
    }
}
