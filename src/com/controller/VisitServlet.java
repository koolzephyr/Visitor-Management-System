package com.controller;

import com.model.Report;
import com.model.Visit;
import com.service.PersonService;
import com.service.VisitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by anons on 6/27/16.
 */
@WebServlet(name = "VisitServlet")
public class VisitServlet extends HttpServlet {
    int userId;
    VisitService vService = null;
    PersonService pService = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String task =request.getParameter("task");
        Visit visit = null;
        userId = (Integer)session.getAttribute("id");

        if(task==null){
            String comment = request.getParameter("comment");
            try{
                vService = new VisitService();

                visit = Visit.getVisit(vService.getUniqueVisitId(),userId,new java.sql.Date(Calendar.getInstance().getTimeInMillis()),new Time(Calendar.HOUR),comment);

                if (vService.storeVisit(visit)){
                    vService.closeConnection();
                    response.sendRedirect("/visit?status=success");
                } else {
                    response.sendRedirect("/visit?status=error");
                    vService.closeConnection();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        else if(task.equals("entry")){
            vService=new VisitService();
            pService=new PersonService();
            if (vService.loggedVisit(userId,new java.sql.Date(Calendar.DATE))){
                request.setAttribute("logged","log");
            }
            vService.closeConnection();
            request.setAttribute("type",pService.getUserType(userId));
            pService.closeConnection();
            request.getRequestDispatcher("/visitlog.jsp").forward(request,response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        userId = (Integer)session.getAttribute("id");
        String action = request.getParameter("action");
        if (action==null){
            vService=new VisitService();
            if (vService.loggedVisit(userId,new java.sql.Date(Calendar.DATE))){
                request.setAttribute("logged","log");
            }
            if (request.getParameter("status").equals("success")){
                request.setAttribute("status","success");
            }
            else if (request.getParameter("status").equals("error")){
                request.setAttribute("status","error");
            }
            vService.closeConnection();
            request.getRequestDispatcher("/visitlog.jsp").forward(request,response);
        }
        else if (action.equals("all")){
            vService=new VisitService();
            pService=new PersonService();
            List<Report> history =vService.getHistory();
            request.setAttribute("history",history);
            vService.closeConnection();
            request.setAttribute("type",pService.getUserType(userId));
            pService.closeConnection();
            request.getRequestDispatcher("/report.jsp").forward(request,response);
        }
        else if (action.equals("my")){
            vService=new VisitService();
            pService=new PersonService();
            List<Report> history = vService.getMyHistory(userId);
            request.setAttribute("history",history);
            vService.closeConnection();
            request.setAttribute("type",pService.getUserType(userId));
            pService.closeConnection();
            request.getRequestDispatcher("/report.jsp").forward(request,response);
        }

    }
}
