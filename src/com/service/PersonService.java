package com.service;



import com.model.Person;
import com.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by anons on 5/10/16.
 */
public class PersonService {
    Connection connection;
    PreparedStatement statement;
    String query;
    ResultSet resultSet;
    public PersonService(){
        try {
            connection= new Database().getDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean storePerson(Person person){
        query = "insert into users values(?,?,?,?,?,?,?,?,?,?)";
        int updated = 0;
        try {
            statement=connection.prepareStatement(query);
            statement.setInt(1,person.getpId());
            statement.setString(2,person.getFirstName());
            statement.setString(3,person.getMiddleName());
            statement.setString(4,person.getLastName());
            statement.setString(5,person.getUsername());
            statement.setString(6,person.getPassword());
            statement.setString(7,person.getAddress());
            statement.setString(8,person.getContact());
            statement.setString(9,person.getEmail());
            statement.setString(10,person.getType());
            updated=statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated != 0;
    }

    public int getUniquePersonId(){
        query = "select pid from users order by pid desc limit 1";
        int pid=1;
        try {
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            if (resultSet.next()){
                pid= resultSet.getInt("pid");
                pid++;
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
        return pid;
    }

   /* public boolean validateUname(String uname){
        query = "select * from users where username = ?";
        boolean unique =false;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,uname);
            resultSet=statement.executeQuery();
            unique = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return unique;
    }*/

    public String getUserType(int pId){
        query = "select type from users where pid = ?";
        String type="";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,pId);
            resultSet=statement.executeQuery();
            if (resultSet.next()){
                type=resultSet.getString("type");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Person getPerson(int pId){
        query = "select * from users where pid = ?";
        Person person = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1,pId);
            resultSet=statement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("pId");
                String fname=resultSet.getString("firstname");
                String mname = resultSet.getString("middlename");
                String lname = resultSet.getString("lastname");
                String uname = resultSet.getString("username");
                String pass = resultSet.getString("password");;
                String address = resultSet.getString("address");
                String contact = resultSet.getString("contact");
                String email = resultSet.getString("email");
                String type = resultSet.getString("type");
                person = Person.getPerson(id,fname,mname,lname,uname,pass,address,contact,email,type);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
}
