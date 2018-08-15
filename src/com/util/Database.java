package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Sameer on 11/4/2014.
 */
public class Database {

    private Connection dbConnection;

    public Database() throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3306/jsp_visitor";
        String user = "library";
        String password = "library";
        dbConnection = DriverManager.getConnection(url, user, password);
    }

    public Connection getDbConnection() {
        return dbConnection;
    }
}
