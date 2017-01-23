package com.ironyard.servlets;

import com.ironyard.data.Ticket;
import com.ironyard.data.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by osmanidris on 1/17/17.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Integer noOfWinningTickets = new Integer(0);
        User user = new User(username,password);
        if (user.isAuthenticated()) {
            Ticket winningTicket = new Ticket();
            request.getSession().setAttribute("winningTicket", winningTicket);
            request.getSession().setAttribute("user", username);
            request.getSession().setAttribute("noOfWinningTickets",noOfWinningTickets);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            request.setAttribute("result", "Invalid username or password");
            doGet(request, response);
        }
    }

}