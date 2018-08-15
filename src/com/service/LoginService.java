package com.service;


import com.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by anons on 5/10/16.
 */
public class LoginService {
    Connection connection;
    PreparedStatement statement;
    String query;
    ResultSet resultSet;

    public LoginService(){
        try {
            connection = new Database().getDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int validateUser(String username, String password){
        query="select pid from users where username = ? and password = ?";
        int pid=0;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                pid=resultSet.getInt("pid");
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

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
