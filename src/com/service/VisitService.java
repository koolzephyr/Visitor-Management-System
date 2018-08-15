package com.service;



import com.model.Person;
import com.model.Report;
import com.util.Database;
import com.model.Visit;

import java.sql.*;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by anons on 5/11/16.
 */
public class VisitService {
    Connection connection;
    PreparedStatement statement;
    String query;
    ResultSet resultSet;
    public VisitService(){
        try {
            connection = new Database().getDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean storeVisit(Visit visit){
        query = "insert into visit values (?,?,?,?,?)";
        int updated = 0;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,visit.getVisitId());
            statement.setInt(2,visit.getPersonId());
            statement.setTime(3, visit.getVisitTime());
            statement.setDate(4, visit.getVisitDate());
            statement.setString(5,visit.getVisitPurpose());
            updated = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated != 0;
    }
    public int getUniqueVisitId(){
        query = "select id from visit order by id desc limit 1";
        int id=1;
        try {
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            if (resultSet.next()){
                id= resultSet.getInt("id");
                id++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public boolean loggedVisit(int id,Date date){
        query = "select id from visit where pid = ? and visitdate = ?";
        boolean record = false;
        try {
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.setDate(2,date);
            resultSet=statement.executeQuery();

            if (resultSet.next()){
                record = true;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    public List<Report> getMyHistory(int id){
        List<Report> visits = new LinkedList<>();
        query = "select * from visit where pid = ? limit 15";
        Report report = null;
        try{
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            Person person = new PersonService().getPerson(id);
            String name = person.getFirstName()+person.getMiddleName()+person.getLastName();
            String contact = person.getContact();
            String type = person.getType();
            while (resultSet.next()){
                int vId = resultSet.getInt("id");
                Date vDate = resultSet.getDate("visitdate");
                Time vTime = resultSet.getTime("visittime");
                String purpose = resultSet.getString("purpose");
                report = Report.getReport(vId,vDate,vTime,name,contact,type,purpose);
                visits.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visits;
    }
    public List<Report> getHistory(){
        List<Report> visits = new LinkedList<>();
        Calendar cal = Calendar.getInstance();
        Date today = new Date(cal.getTimeInMillis());
        cal.add(Calendar.DATE,-30);
        cal.setTime(cal.getTime());
        Date monthAgo = new Date(cal.getTimeInMillis());
        query = "select * from visit where visitdate <= ? and visitdate >=?";
        Report report = null;
        try{
            statement=connection.prepareStatement(query);
            statement.setDate(1,today);
            statement.setDate(2,monthAgo);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                Date vDate = resultSet.getDate("visitdate");
                Time vTime = resultSet.getTime("visittime");
                int pId = resultSet.getInt("pid");
                Person person = new PersonService().getPerson(pId);
                String name = person.getFirstName()+person.getMiddleName()+person.getLastName();
                String contact = person.getContact();
                String type = person.getType();
                String purpose = resultSet.getString("purpose");
                report = Report.getReport(id,vDate,vTime,name,contact,type,purpose);
                visits.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visits;
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
