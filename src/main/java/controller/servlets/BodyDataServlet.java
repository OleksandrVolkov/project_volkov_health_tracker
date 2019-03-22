package controller.servlets;

import controller.actions.*;
import controller.actions.body_actions.*;
import controller.actions.load_actions.LoadDishesToCheck;
import controller.actions.load_actions.LoadNormValuesAction;
import controller.actions.load_actions.LoadToAddCustomDishAction;
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
    private Map<String,Action> actionMap = new HashMap<String,Action>();
    private View view;

    @Override
    public void init(ServletConfig config) throws ServletException {
        actionMap.put("addNewDish", new AddNewDishAction());
        actionMap.put("aboutYourself", new ShowInfoAboutYourselfAction());
        actionMap.put("normIndicators", new ShowNormIndicatorsAction());
        actionMap.put("checkDiet", new CheckDietAction());
        actionMap.put("update_user_data", new UpdateUserDataAction());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionStr = req.getParameter("action");

        ActionHandler actionHandler = new ActionHandler(req, resp);
        actionHandler.execute(actionMap, actionStr);

        view = new View(req, resp);
        view.redirectToPage(actionHandler.getRequestUrl());
    }
}
