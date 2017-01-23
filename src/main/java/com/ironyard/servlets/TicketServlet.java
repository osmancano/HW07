package com.ironyard.servlets;

import com.ironyard.data.Ticket;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by osmanidris on 1/18/17.
 */
@WebServlet(name = "TicketServlet", urlPatterns = "/generateTicket")
public class TicketServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        String paramName;
        for(int i=0; i < 6 ;i++){
            paramName = "num"+i;
            System.out.println(paramName);
            nums.add(Integer.parseInt(request.getParameter(paramName)));
        }
        Ticket ticket = null;
        try {
            ticket = new Ticket(nums);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer noOfWinningTickets = (Integer) request.getSession().getAttribute("noOfWinningTickets");
        Ticket winningTichet = (Ticket) request.getSession().getAttribute("winningTicket");
        if(winningTichet == null){
            winningTichet = new Ticket();
            request.getSession().setAttribute("winningTicket", winningTichet);
        }
        ArrayList<Ticket> myList = null;
        myList = (ArrayList<Ticket>) request.getSession().getAttribute("ticket_list");
        if(myList == null){
            // need to create
            myList = new ArrayList<>();
            // only when new list
            request.getSession().setAttribute("ticket_list", myList);
        }
        if(ticket.isWinningTicket(winningTichet)){
            noOfWinningTickets = new Integer(noOfWinningTickets.intValue()+1);
            request.getSession().setAttribute("noOfWinningTickets",noOfWinningTickets);
        }
        // add to list
        myList.add(ticket);
        String nextJSP = "/home.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Ticket ticket = new Ticket();
        Ticket winningTichet = (Ticket) request.getSession().getAttribute("winningTicket");
        if(winningTichet == null){
            winningTichet = new Ticket();
            request.getSession().setAttribute("winningTicket", winningTichet);
        }
        Integer noOfWinningTickets = (Integer) request.getSession().getAttribute("noOfWinningTickets");
        ArrayList<Ticket> myList = null;
        myList = (ArrayList<Ticket>) request.getSession().getAttribute("ticket_list");
        if(myList == null){
            // need to create
            myList = new ArrayList<>();
            // only when new list
            request.getSession().setAttribute("ticket_list", myList);
        }
        // check if it is winning ticket
        if(ticket.isWinningTicket(winningTichet)){
            noOfWinningTickets = new Integer(noOfWinningTickets.intValue()+1);
            request.getSession().setAttribute("noOfWinningTickets",noOfWinningTickets);
        }
        // add to list
        myList.add(ticket);
        // forward to JSP
        String nextJSP = "/home.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }
}
