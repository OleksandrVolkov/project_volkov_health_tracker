package controller.actions.body_actions;

import controller.actions.Action;
import model.data.services.UserService;
import model.entities.User;
import model.entities.enums.Lifestyle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserDataAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String heightStr = request.getParameter("height");
        String weightStr = request.getParameter("weight");
        String ageStr = request.getParameter("age");
        String email = request.getParameter("email");
        String lifestyleStr = request.getParameter("lifestyle");
        Lifestyle lifestyle = null;
        if(!lifestyleStr.equals(""))
            lifestyle = Lifestyle.lookup(lifestyleStr);
        Double height = 0.0;
        Double weight = 0.0;
        Integer age = 0;

        //TODO: method numbechecking
        if(heightStr == null || heightStr == ""){

        } else{
            height = Double.parseDouble(heightStr);
        }
        if(weightStr == null || weightStr == ""){

        } else{
            weight = Double.parseDouble(weightStr);
        }
        if(ageStr == null || ageStr == ""){

        } else{
            age = Integer.parseInt(ageStr);
        }





//        System.out.println(name);
//        System.out.println(surname);
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(height);
//        System.out.println(weight);
//        System.out.println(age);

        HttpSession httpSession = request.getSession();
        String currentUsername = (String) httpSession.getAttribute("LOGGED_USER");
        UserService userService = new UserService();
        User oldUser = userService.findUserByUsername(currentUsername);
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
                .build();

        User finalUser = getUpdatedUser(oldUser, newUser);
        userService.updateUserById(finalUser, finalUser.getId());

        if(!finalUser.getUsername().equals(oldUser.getUsername()))
            httpSession.setAttribute("LOGGED_USER", finalUser.getUsername());



        return "/body?action=aboutYourself";
    }

    public User getUpdatedUser(User oldUser, User newUser){
        System.out.println(oldUser);
        newUser.setId(oldUser.getId());

        if(newUser.getAge() == 0)
            newUser.setAge(oldUser.getAge());
        if(newUser.getHeight() == 0)
            newUser.setHeight(oldUser.getHeight());
        if(newUser.getWeight() == 0)
            newUser.setWeight(oldUser.getWeight());
        if(newUser.getUsername() == "")
            newUser.setUsername(oldUser.getUsername());
        if(newUser.getLifestyle() == null)
            newUser.setLifestyle(oldUser.getLifestyle());
        if(newUser.getName() == "")
            newUser.setName(oldUser.getName());
        if(newUser.getSurname() == "")
            newUser.setSurname(oldUser.getSurname());
        if(newUser.getPassword() == "")
            newUser.setPassword(oldUser.getPassword());
        if(newUser.getSex() == null)
            newUser.setSex(oldUser.getSex());
        if(newUser.getEmail() == "")
            newUser.setEmail(oldUser.getEmail());

        return newUser;
    }
}
