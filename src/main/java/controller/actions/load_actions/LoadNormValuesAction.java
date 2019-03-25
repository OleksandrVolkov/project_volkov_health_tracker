package controller.actions.load_actions;

import controller.actions.Action;
import controller.utility.FoodCalculator;
import controller.utility.LanguageHandler;
import model.data.services.UserService;
import model.entities.Nutrients;
import model.entities.User;
import model.entities.enums.Language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

//TODO:: UNIQUE VALUES IN THE DATABASE

public class LoadNormValuesAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = "/view/norm_values.jsp";

        FoodCalculator foodCalculator = new FoodCalculator();
        HttpSession httpSession = request.getSession();
        String username = (String) httpSession.getAttribute("LOGGED_USER");
        UserService userService = new UserService();
        User user = userService.findUserByUsername(username);

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

        request.setAttribute("callories", callories);
        request.setAttribute("nutrients", nutrients);
        request.setAttribute("amr", amr);
        request.setAttribute("bmr", bmr);

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

    public Double getRoundedValue(double val){
        return BigDecimal.valueOf(val).
                setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
