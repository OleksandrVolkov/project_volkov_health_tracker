package controller.servlets;

import controller.actions.Action;
import controller.actions.ActionHandler;
import controller.actions.account_actions.LogInAction;
import controller.actions.account_actions.LogOutAction;
import controller.actions.account_actions.RegisterAction;
import controller.actions.account_actions.ValidateFormAction;
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

@WebServlet(name = "account", urlPatterns = {"/account"})
public class AccountServlet extends HttpServlet{
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(LoadToAddCustomDishAction.class);

    private Map<String,Action> actionMap = new HashMap<String,Action>();
    private View view;

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.trace("Putting values to action map");
        actionMap.put("logout", new LogOutAction());
        actionMap.put("login", new LogInAction());
        actionMap.put("validate", new ValidateFormAction());
        actionMap.put("register", new RegisterAction());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("Account controller");
        String actionKey = req.getParameter("action");

        log.trace("Action string: " + actionKey);
        ActionHandler actionHandler = new ActionHandler(req, resp);
        log.trace("Executing action");
        actionHandler.execute(actionMap, actionKey);

        log.trace("Redirecting");
        view = new View(req, resp);
        view.redirectToPage(actionHandler.getRequestUrl());
    }
}
