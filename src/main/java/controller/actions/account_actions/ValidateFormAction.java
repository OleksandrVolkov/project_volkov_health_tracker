package controller.actions.account_actions;

import controller.actions.Action;
import controller.utility.LanguageHandler;
import model.data.services.UserService;
import model.entities.User;
import model.entities.enums.Language;
import model.entities.enums.Sex;
import controller.utility.Validator;
import model.entities.enums.Lifestyle;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *  <h1>ValidateFormAction class</h1>
 *  ValidateFormAction is an implementation class of the Action interface.
 *  It validates form input values in order to verify whether user can be registered.
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class ValidateFormAction implements Action {
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(ValidateFormAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("HERE");
        log.trace("Validating form");
        Validator validator = new Validator();
        UserService userService = new UserService();
        System.out.println("HERE");

        log.trace("Getting parameters from the request");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        String password = request.getParameter("psw");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        String email = request.getParameter("email");
        String sexStr = request.getParameter("sex");
        String lifestyleStr = request.getParameter("lifestyle");
        Lifestyle lifestyle = Lifestyle.lookup(lifestyleStr);
        System.out.println("HERE");
            Sex sex = Sex.lookup(sexStr);

        Double heightD = null;
        Double weightD = null;
        Integer ageI = null;

            boolean isValidEmail = true;
            boolean isValidUsername = true;
            boolean isValidName = true;
            boolean isValidSurname = true;
            boolean isValidPassword = true;

            //TODO: create regular expressions for: //AND IS BUSY
            boolean isValidHeight = true;
            boolean isValidWeight = true;
            boolean isValidAge = true;
            boolean isNotTakenEmail = true;
            boolean isNotTakenUsername = true;
        System.out.println("HERE");
            log.trace("Validating parameters");
            if (!validator.isValidEmail(email)) {
                System.out.println("EMAIL");
                isValidEmail = false;
                log.trace("Email is invalid");
            }
            if(!validator.isValidDouble(height)){
                System.out.println("HEIGHT");
                isValidHeight = false;
                log.trace("Height is invalid");
            } else {
                heightD = Double.parseDouble(height);
            }
            if(!validator.isValidDouble(weight)){
                System.out.println("WEIGHT");
                isValidWeight = false;
                log.trace("Weight is invalid");
            } else {
                weightD = Double.parseDouble(weight);
            }
            if(!validator.isValidInteger(age)){
                System.out.println("AGE");
                isValidAge = false;
                log.trace("Age is invalid");
            } else {
                ageI = Integer.parseInt(age);
            }
            if (!validator.isValidUsername(username)) {
                System.out.println("USERNAME");
                isValidUsername = false;
                log.trace("Username is invalid");
            }
            if (!validator.isValidName(name)) {
                System.out.println("NAME");
                isValidName = false;
                log.trace("Name is invalid");
            }
            if (!validator.isValidSurname(surname)) {
                isValidSurname = false;
                log.trace("Surname is invalid");
            }
            if (!validator.isValidPassword(password)) {
                isValidPassword = false;
                log.trace("Password is invalid");
            }
            if (userService.isEmailTaken(email)) {
                isNotTakenEmail = false;
                log.trace("Email is already taken");
            }
            if (userService.isUsernameTaken(username)) {
                isNotTakenUsername = false;
                log.trace("Username is already taken");
            }

        System.out.println("HERE");

            log.trace("Verifying whether all boolean values are correct");
            String url = "";
            if (isValidName == false || isValidSurname == false || isValidPassword == false || isValidUsername == false || isValidEmail == false ||
                    isValidHeight == false || isValidWeight == false || isValidAge == false || isNotTakenEmail == false || isNotTakenUsername == false) {
                log.trace("They are not correct");
                log.trace("Setting attributes");
                request.setAttribute("isValidEmail", isValidEmail);
                request.setAttribute("isValidUsername", isValidUsername);
                request.setAttribute("isValidName", isValidName);
                request.setAttribute("isValidSurname", isValidSurname);
                request.setAttribute("isValidPassword", isValidPassword);
                request.setAttribute("isValidHeight", isValidHeight);
                request.setAttribute("isValidWeight", isValidWeight);
                request.setAttribute("isValidAge", isValidAge);
                request.setAttribute("isNotTakenUsername", isNotTakenUsername);
                request.setAttribute("isNotTakenEmail", isNotTakenEmail);




                log.trace("Getting language value");
                LanguageHandler languageHandler = new LanguageHandler();
                String lang = languageHandler.getLangValue(request, request.getParameter("lang"));

                log.trace("Getting registration properties");
                Map<String, String> regProps = LanguageHandler.getHashMapOfValuesByPageUrl("/view/registration_form.jsp", Language.getLanguage(lang));
//        regProps.put("lang", lang);
                System.out.println(regProps);

                request.setAttribute("language", regProps);





                log.trace("Returning url");
                return "/view/registration_form.jsp";


//                return url;
            } else {
                log.trace("They are totally correct");
                log.trace("Building user");
                User user = new User.Builder()
                        .withAge(ageI)
                        .withEmail(email)
                        .withHeight(heightD)
                        .withLifestyle(lifestyle)
                        .withName(name)
                        .withSurname(surname)
                        .withPassword(password)
                        .withWeight(weightD)
                        .withSex(sex)
                        .withUsername(username)
                        .build();
                log.trace("Setting user attribute");
//                request.getSession().setAttribute("user", user);
//                url = "/account?action=register";

                log.info("Registering an account");
//                UserService userService = new UserService();
                log.trace("Getting user as the request attribute");
//                User user = (User) request.getSession().getAttribute("user");
                log.trace("Creating user");
                userService.createUser(user);
                log.trace("Setting attribute LOGGED_USER");
                request.getSession().setAttribute("LOGGED_USER", user.getUsername());
                log.trace("Returning url");
                return "/load_data?action=load_cabinet";
            }
//            log.trace("Returning url: " + url);
//            return url;
    }



}
