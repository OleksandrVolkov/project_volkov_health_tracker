package controller.actions;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ActionHandler {
    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;
    private String url;

    public ActionHandler(HttpServletRequest req, HttpServletResponse resp){
        this.httpRequest = req;
        this.httpResponse = resp;
    }

    public void execute(Map<String, Action> actionMap, String actionStr){
        Action action = actionMap.get(actionStr);
        try {
            this.url = action.execute(httpRequest,httpResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRequestUrl(){
        return url;
    }
}
