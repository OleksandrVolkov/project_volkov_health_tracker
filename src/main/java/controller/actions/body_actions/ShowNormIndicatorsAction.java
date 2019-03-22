package controller.actions.body_actions;

import controller.actions.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowNormIndicatorsAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("SHOWING NORM INDICATORS...");
        return null;
    }
}
