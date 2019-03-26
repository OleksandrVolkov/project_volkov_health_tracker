package controller.actions.body_actions;

import controller.actions.Action;
import controller.actions.account_actions.ValidateFormAction;
import model.data.services.UserService;
import model.entities.User;
import model.entities.enums.Lifestyle;
import model.entities.enums.Sex;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserDataAction implements Action {
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(ValidateFormAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("Updating user data");
        log.trace("Getting parameters from the request");
        String name = request.getParameter("name");
        System.out.println(name + "NAME");
        String surname = request.getParameter("surname");
        System.out.println(surname + "SUR");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String heightStr = request.getParameter("height");
        String weightStr = request.getParameter("weight");
        String ageStr = request.getParameter("age");
        String email = request.getParameter("email");
        String lifestyleStr = request.getParameter("lifestyle");
        Sex sex = Sex.lookup(request.getParameter("sex"));
        Lifestyle lifestyle = Lifestyle.lookup(lifestyleStr);

        Double height = 0.0;
        Double weight = 0.0;
        Integer age = 0;

        log.trace("Parsing double values");
        if(heightStr != null && !heightStr.equals("")){
            height = Double.parseDouble(heightStr);
        }
        if(weightStr != null && !weightStr.equals("")){
            weight = Double.parseDouble(weightStr);
        }
        if(ageStr != null && !ageStr.equals("")){
            age = Integer.parseInt(ageStr);
        }

        log.trace("Getting username from the session");
        HttpSession httpSession = request.getSession();
        String currentUsername = (String) httpSession.getAttribute("LOGGED_USER");
        UserService userService = new UserService();
        log.trace("Finding user by username = " + username);
        User oldUser = userService.findUserByUsername(currentUsername);
        log.trace("Building user");
        User newUser = new User.Builder()
                .withName(name)
                .withSurname(surname)
                .withUsername(username)
                .withPassword(password)
                .withHeight(height)
                .withWeight(weight)
                .withAge(age)
                .withEmail(email)
                .withLifestyle(lifestyle)
                .withSex(sex)
                .build();

        log.trace("Getting updated user");
        User finalUser = getUpdatedUser(oldUser, newUser);
        log.trace("Updating user by id : " + finalUser.getId());
        userService.updateUserById(finalUser, finalUser.getId());

        if(!finalUser.getUsername().equals(oldUser.getUsername())) {
            log.trace("Changing LOGGED_USER");
            httpSession.setAttribute("LOGGED_USER", finalUser.getUsername());
        }

        log.trace("Returning url");
        return "/load_data?action=load_info";
    }

    public User getUpdatedUser(User oldUser, User newUser){
        log.trace("Getting updated user");
        newUser.setId(oldUser.getId());

        log.trace("Checking whether fields are empty");
        if(newUser.getAge() == 0)
            newUser.setAge(oldUser.getAge());
        if(newUser.getHeight() == 0)
            newUser.setHeight(oldUser.getHeight());
        if(newUser.getWeight() == 0)
            newUser.setWeight(oldUser.getWeight());
        if(newUser.getUsername()== null || newUser.getUsername().equals(""))
            newUser.setUsername(oldUser.getUsername());
        if(newUser.getLifestyle() == null)
            newUser.setLifestyle(oldUser.getLifestyle());
        if(newUser.getName() == null || newUser.getName().equals(""))
            newUser.setName(oldUser.getName());
        if(newUser.getSurname()==null || newUser.getSurname().equals(""))
            newUser.setSurname(oldUser.getSurname());
        if(newUser.getPassword()==null || newUser.getPassword().equals(""))
            newUser.setPassword(oldUser.getPassword());
        if(newUser.getSex() == null)
            newUser.setSex(oldUser.getSex());
        if(newUser.getEmail()==null || newUser.getEmail().equals(""))
            newUser.setEmail(oldUser.getEmail());
        if(newUser.getSex()==null)
            newUser.setSex(oldUser.getSex());
        log.trace("Returning updated user value");
        return newUser;
    }
}
