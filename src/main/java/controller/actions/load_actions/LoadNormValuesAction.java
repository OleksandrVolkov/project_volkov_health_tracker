package controller.actions.load_actions;

import controller.actions.Action;
import controller.utility.FoodCalculator;
import controller.utility.LanguageHandler;
import model.data.services.UserService;
import model.entities.Nutrients;
import model.entities.User;
import model.entities.enums.Language;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

//TODO:: UNIQUE VALUES IN THE DATABASE

public class LoadNormValuesAction implements Action {
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(LoadNormValuesAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Loading norm values");
        String url = "/view/norm_values.jsp";

        FoodCalculator foodCalculator = new FoodCalculator();
        log.trace("Getting username from the session");
        HttpSession httpSession = request.getSession();
        String username = (String) httpSession.getAttribute("LOGGED_USER");
        UserService userService = new UserService();
        log.trace("Finding user by username = " + username);
        User user = userService.findUserByUsername(username);

        log.trace("Getting double values");
        Double preCallories = foodCalculator.neededCallories(user);

        Nutrients nutrients = foodCalculator.getNeededNutrients(preCallories);
        Double preBmr = foodCalculator.getBMR(user);
        Double preAmr = foodCalculator.getAMR(user);

        Double callories = getRoundedValue(preCallories);
        Double amr = getRoundedValue(preAmr);
        Double bmr = getRoundedValue(preBmr);
        nutrients.setProteins(getRoundedValue(nutrients.getProteins()));
        nutrients.setCarbohydrates(getRoundedValue(nutrients.getCarbohydrates()));
        nutrients.setFats(getRoundedValue(nutrients.getFats()));

        log.trace("Setting attributes to the request");
        request.setAttribute("callories", callories);
        request.setAttribute("nutrients", nutrients);
        request.setAttribute("amr", amr);
        request.setAttribute("bmr", bmr);

        log.trace("Getting language value");
        LanguageHandler languageHandler = new LanguageHandler();
        String lang = languageHandler.getLangValue(request, request.getParameter("lang"));

        log.trace("Getting norm values");
        Map<String, String> normValues = LanguageHandler.getHashMapOfValuesByPageUrl(url, Language.getLanguage(lang));

        request.setAttribute("language", normValues);

        log.trace("Returning url: " + url);
        return url;
    }
    public Double getRoundedValue(double val){
        log.trace("Getting rounded value");
        return BigDecimal.valueOf(val).
                setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
