package controller.actions;

import com.sun.deploy.net.HttpRequest;
import controller.actions.load_actions.LoadToAddCustomDishAction;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ActionHandler {
    /**
     * This is a logger to write log messages during the execution of a program
     */
    private static Logger log = Logger.getLogger(ActionHandler.class);

    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;
    private String url;

    public ActionHandler(HttpServletRequest req, HttpServletResponse resp){
        this.httpRequest = req;
        this.httpResponse = resp;
    }

    public void execute(Map<String, Action> actionMap, String actionStr){
        log.info("Getting action");
        Action action = actionMap.get(actionStr);
        try {
            log.trace("Executing...");
            this.url = action.execute(httpRequest,httpResponse);
        } catch (Exception e) {
            log.warn("Exception caught: " + e);
        }
    }

    public String getRequestUrl(){
        return url;
    }
}
