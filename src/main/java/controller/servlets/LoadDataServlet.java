package controller.servlets;

import controller.actions.Action;
import controller.actions.ActionHandler;
import controller.actions.load_actions.*;
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

@WebServlet(name = "/load_data", urlPatterns = {"/load_data"})
public class LoadDataServlet extends HttpServlet{
    private Map<String,Action> actionMap = new HashMap<String,Action>();
    private View view;

    @Override
    public void init(ServletConfig config) throws ServletException {
        actionMap.put("loadToCheckDiet", new LoadDishesToCheck());
        actionMap.put("load_to_add", new LoadToAddCustomDishAction());
        actionMap.put("load_norm_values", new LoadNormValuesAction());
        actionMap.put("load_registration", new LoadRegistrationPropertiesAction());
        actionMap.put("load_lang", new LoadLanguageAction());
        actionMap.put("load_login", new LoadLoginFormAction());
        actionMap.put("load_cabinet", new LoadCabinetValues());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionStr = req.getParameter("action");
        System.out.println("ACTION: " + actionStr);
        ActionHandler actionHandler = new ActionHandler(req, resp);
        actionHandler.execute(actionMap, actionStr);

        view = new View(req, resp);
        view.redirectToPage(actionHandler.getRequestUrl());
    }
}
