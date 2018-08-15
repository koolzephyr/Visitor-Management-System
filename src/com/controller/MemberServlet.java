package com.controller;

import com.model.Person;
import com.service.LoginService;
import com.service.PersonService;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by anons on 5/25/16.
 */
public class MemberServlet extends javax.servlet.http.HttpServlet {
    LoginService lService;
    PersonService pService;
    int userId =0;
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String operation = request.getParameter("operation");
        String username = request.getParameter("user");
        String pass = request.getParameter("pass");
        HttpSession session = request.getSession();
        switch (operation) {
            case "login":
                lService = new LoginService();
                userId = lService.validateUser(username, pass);
                lService.closeConnection();
                if (userId != 0) {
                    session.setAttribute("id",userId);
                    request.getRequestDispatcher("/visit?task=entry").forward(request, response);
                } else {
                    request.setAttribute("status", "error");
                    request.getRequestDispatcher("/").forward(request, response);
                }

                break;
            case "register":
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                break;
            case "store":
                pService = new PersonService();
                int id = pService.getUniquePersonId();

                String fName = request.getParameter("fname");
                String mName = request.getParameter("mname");
                String lName = request.getParameter("lname");
                String addrss = request.getParameter("add");
                String cntct = request.getParameter("contact");
                String eml = request.getParameter("email");
                String typ = request.getParameter("type");
                System.out.println(username);
                username = fName.toLowerCase() + '.' + lName.toLowerCase();
                Person person = Person.getPerson(id, fName, mName, lName, username, pass, addrss, cntct, eml, typ);
                if (pService.storePerson(person)) {
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("status", "error");
                }
                pService.closeConnection();
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("status", "error");
                request.getRequestDispatcher("/").forward(request, response);
                break;
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("id");
        request.getRequestDispatcher("/").forward(request,response);
    }
}
