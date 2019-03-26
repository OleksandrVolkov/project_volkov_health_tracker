package controller.servlets;

import controller.actions.*;
import controller.actions.body_actions.*;
import controller.actions.load_actions.LoadDishesToCheck;
import controller.actions.load_actions.LoadNormValuesAction;
import controller.actions.load_actions.LoadToAddCustomDishAction;
import org.apache.log4j.Logger;
import view.View;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "body", urlPatterns = {"/body"})
public class BodyDataServlet extends HttpServlet{
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(LoadToAddCustomDishAction.class);

    private Map<String,Action> actionMap = new HashMap<String,Action>();
    private View view;

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.trace("Putting values to action map");
        actionMap.put("addNewDish", new AddNewDishAction());
//        actionMap.put("aboutYourself", new ShowInfoAboutYourselfAction());
        actionMap.put("checkDiet", new CheckDietAction());
        actionMap.put("update_user_data", new UpdateUserDataAction());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("Body controller");
        String actionStr = req.getParameter("action");

        log.trace("Action string: " + actionStr);
        ActionHandler actionHandler = new ActionHandler(req, resp);
        log.trace("Executing action");
        actionHandler.execute(actionMap, actionStr);

        log.trace("Redirecting");
        view = new View(req, resp);
        view.redirectToPage(actionHandler.getRequestUrl());
    }
}
