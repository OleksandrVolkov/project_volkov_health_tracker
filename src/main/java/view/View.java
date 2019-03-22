package view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class View {
    HttpServletRequest request;
    HttpServletResponse response;

    public View(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    public void redirectToPage(String url){
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//Redirectable interface
//class Redirector
