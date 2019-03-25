package controller.actions.load_actions;

import controller.actions.Action;
import controller.utility.LanguageHandler;
import model.data.services.DishTypeService;
import model.entities.DishType;
import model.entities.enums.Language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class LoadToAddCustomDishAction implements Action{
//TODO:: CHANGE SESSIONS OR COOKIES!!!!! DON'T FORGET!!!!!!!!
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = "/view/add_custom_dish.jsp";
        DishTypeService dishTypeService = new DishTypeService();
        List<DishType> dishTypes = dishTypeService.findAllDishTypes();
        request.setAttribute("dishTypes", dishTypes);

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

        return url;
    }
}
