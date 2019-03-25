package controller.actions.load_actions;

import controller.actions.Action;
import controller.utility.LanguageHandler;
import model.entities.enums.Language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoadLanguageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getParameter("url");
        String lang = request.getParameter("lang");

        if(url == null || url.equals(""))
            url = "/view/cabinet.jsp";

        if(lang == null || lang.equals(""))
            lang = "en";


//        System.out.println("LANG " + lang);



        Map<String, String> authForm = LanguageHandler.getHashMapOfValuesByPageUrl(url, Language.getLanguage(lang));
        authForm.put("lang", lang);


//        System.out.println(authForm+"________");
        request.setAttribute("language", authForm);
        return url;
    }
}
