package controller.actions.account_actions;

import controller.actions.Action;
import model.data.dao.UserDAO;
import model.data.services.UserService;
import model.entities.User;
import model.entities.enums.Sex;
import model.utility.Validator;
import model.entities.enums.Lifestyle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Validator validator = new Validator();
        UserService userService = new UserService();

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
        System.out.println("|||||||||||||||||" + lifestyle);

        System.out.println("!!!!!!" + sexStr);
        Sex sex = Sex.lookup(sexStr);
        System.out.println("?????" + sex);

        Double heightD = Double.parseDouble(height);
        Double weightD = Double.parseDouble(weight);
        Integer ageI = Integer.parseInt(age);

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

        if(!validator.isValidEmail(email)) {
            isValidEmail = false;
            System.out.println("NOT VALID EMAIL ");
        }
        if(!validator.isValidUsername(username)) {
            isValidUsername = false;
            System.out.println("NOT VALID USERNAME ");
        }
        if(!validator.isValidName(name)){
            isValidName = false;
            System.out.println("NOT VALID NAME ");
        }
        if(!validator.isValidSurname(surname)) {
            isValidSurname = false;
            System.out.println("NOT VALID SURNAME ");
        }
        if(!validator.isValidPassword(password)) {
            isValidPassword = false;
            System.out.println("NOT VALID PASSWORD ");
        }
        if(userService.isEmailTaken(email)) {
            isNotTakenEmail = false;
            System.out.println("EMAIL IS ALREADY TAKEN");
        }
        if(userService.isUsernameTaken(username)) {
            isNotTakenUsername = false;
            System.out.println("USERNAME IS ALREADY TAKEN");
        }




        String url = "";
        if (isValidName == false || isValidSurname == false || isValidPassword == false || isValidUsername == false || isValidEmail == false ||
                isValidHeight == false || isValidWeight == false || isValidAge == false || isNotTakenEmail == false || isNotTakenUsername == false) {
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

            url = "/view/registration_form.jsp";
        } else {
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
            System.out.println("_________"+user.getSex());
            userService.createUser(user);

            request.getSession().setAttribute("LOGGED_USER", username);
            String loginSession = (String) request.getSession().getAttribute("LOGGED_USER");
            System.out.println("SESSION FOUND " + loginSession);
            url = "/view/cabinet.jsp";
        }
        return url;
    }

}
