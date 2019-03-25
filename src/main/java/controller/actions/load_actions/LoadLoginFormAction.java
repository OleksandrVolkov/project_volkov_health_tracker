package controller.actions.load_actions;

import controller.actions.Action;
import controller.utility.LanguageHandler;
import model.entities.enums.Language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoadLoginFormAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = "/view/login_form.jsp";


//        String lang = request.getParameter("lang");
//        if(lang != null && !lang.equals("")){
//            request.getServletContext().setAttribute("lang", lang);
//        } else {
//            request.getServletContext().setAttribute("lang", "en");
//            lang = "en";
//        }
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



        System.out.println("LANG AFTER:  " + lang);

        Map<String, String> authForm = LanguageHandler.getHashMapOfValuesByPageUrl(url, Language.getLanguage(lang));
        authForm.put("lang", lang);

        request.setAttribute("language", authForm);

        return "/view/login_form.jsp";
    }
}
