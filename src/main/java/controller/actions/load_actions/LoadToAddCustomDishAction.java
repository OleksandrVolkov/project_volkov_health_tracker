package controller.actions.load_actions;

import controller.actions.Action;
import controller.utility.LanguageHandler;
import model.data.services.DishTypeService;
import model.entities.DishType;
import model.entities.enums.Language;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class LoadToAddCustomDishAction implements Action{
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(LoadToAddCustomDishAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.trace("Loading to add custom dish");
        LanguageHandler languageHandler = new LanguageHandler();
        String url = "/view/add_custom_dish.jsp";
        DishTypeService dishTypeService = new DishTypeService();
        log.trace("Finding all dish types");
        List<DishType> dishTypes = dishTypeService.findAllDishTypes();
        request.setAttribute("dishTypes", dishTypes);

        log.trace("Getting addDishMap");
        String lang = languageHandler.getLangValue(request, request.getParameter("lang"));
        Map<String, String> getDishMap = LanguageHandler.getHashMapOfValuesByPageUrl(url, Language.getLanguage(lang));

        request.setAttribute("language", getDishMap);
        log.trace("Returning url: " + url);
        return url;
    }
}
