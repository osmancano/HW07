package com.ironyard.data;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by osmanidris on 1/17/17.
 */

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/home.jsp","/index.jsp"})
public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        String username = (String)request.getSession().getAttribute("user");

        if(username != null) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect("/login.jsp");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}