package com.ironyard.servlets;

import com.ironyard.data.Ticket;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by osmanidris on 1/19/17.
 */
@WebServlet(name = "WinningTicketServlet",urlPatterns = "/generateWinningTicket")
public class WinningTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        String paramName;
        for(int i=0; i < 6 ;i++){
            paramName = "num"+i;
            System.out.println(paramName);
            nums.add(Integer.parseInt(request.getParameter(paramName)));
        }
        Ticket winningTicket = null;
        try {
            winningTicket = new Ticket(nums);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //add the winning ticket to the session
        request.getSession().setAttribute("winningTicket", winningTicket);
        //remove the list of tickets from the session
        request.getSession().removeAttribute("ticket_list");
        // forward to JSP
        String nextJSP = "/home.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket winningTicket = new Ticket();
        //add the winning ticket to the session
        request.getSession().setAttribute("winningTicket", winningTicket);
        //remove the list of tickets from the session
        request.getSession().removeAttribute("ticket_list");
        // forward to JSP
        String nextJSP = "/home.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }
}
