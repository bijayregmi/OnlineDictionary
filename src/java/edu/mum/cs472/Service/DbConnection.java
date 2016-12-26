/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs472.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bijayregmi
 */
public class DbConnection {

    public static Connection db_Connect() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connection = "jdbc:mysql://localhost:3306/entries?user=root&password=root";
            conn = DriverManager.getConnection(connection);
            System.out.println("edu.mum.cs472.Service.DbConnection.db_Connect()");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: " + cE.toString());
        }
        return conn;
    }

    public static void db_Close(Connection conn) {

        try {
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();

        }
    }

}
