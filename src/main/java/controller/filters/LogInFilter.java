package controller.filters;


import view.View;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("INIT!!!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String username =(String)httpServletRequest.getSession().getAttribute("LOGGED_USER");
        System.out.println(username + "USERNAME!!!!!!!!!!");
        if(username == null || username.equals("")){
            View view = new View(httpServletRequest, httpServletResponse);
            view.redirectToPage("/load_data?action=load_login");
        } else {
//            httpServletRequest.getSession().setAttribute("LOGGED_USER", username);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println("DESTROY!!!");
    }
}
