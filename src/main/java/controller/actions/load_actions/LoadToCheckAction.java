package controller.actions.load_actions;

import controller.actions.Action;
import controller.utility.LanguageHandler;
import model.entities.enums.Language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoadToCheckAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String url = "/view/check_diet.jsp";
//        LanguageHandler languageHandler = new LanguageHandler();
//        String lang = languageHandler.getLangValue(request, request.getParameter("lang"));
//        Map<String, String> authForm = LanguageHandler.getHashMapOfValuesByPageUrl(url, Language.getLanguage(lang));
//        authForm.put("lang", lang);
//        System.out.println(authForm);
//
//        request.setAttribute("language", authForm);
        return "";
    }
}
