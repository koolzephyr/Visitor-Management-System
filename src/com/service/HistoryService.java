package com.service;



import com.util.Database;
import com.model.Person;
import com.model.Visit;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anons on 5/12/16.
 */
public class HistoryService {
    Connection connection;
    PreparedStatement statement;
    String query;
    ResultSet resultSet;
    List<Visit> visits = new LinkedList<>();
    List<Person> persons = new LinkedList<>();
    public HistoryService(){
        try {
            connection = new Database().getDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Visit> history(int pId){
        query = "select id,visitdate,visittime,purpose from visit where pid =? order by visitdate desc limit 14";
        Visit visit;
        visits.clear();
        try {
            statement=connection.prepareStatement(query);
            statement.setInt(1,pId);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                int vId = resultSet.getInt("id");
                Date vDate = resultSet.getDate("visitdate");
                Time vTime = resultSet.getTime("visittime");
                String purps = resultSet.getString("purpose");
                visit = Visit.getVisit(vId,pId,vDate,vTime,purps);
                visits.add(visit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visits;
    }

    public void allHistory(int pId){
        query = "select v.id,v.visitdate,v.visittime,concat(u.firstname,' ',u.middlename,' ',u.lastname) as name, " +
                "u.contact,u.type,v.purpose from visit v " +
                "join users u on " +
                "v.pid = u.pid " +
                "where v.visitdate BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) and NOW() order by v.visitdate desc limit 30;";
        Visit visit;
        Person person;
        visits.clear();
        try {
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                int vId = resultSet.getInt("id");
                Date vDate = resultSet.getDate("visitdate");
                Time vTime = resultSet.getTime("visittime");
                String name = resultSet.getString("name");
                String contact = resultSet.getString("contact");
                String type = resultSet.getString("type");
                String purps = resultSet.getString("purpose");
                visit = Visit.getVisit(vId,pId,vDate,vTime,purps);
                person = Person.getPerson(pId,name,"","","","","",contact,"",type);
                visits.add(visit);
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public List<Person> getPersons() {
        return persons;
    }
}
