package com.todo;

import com.todo.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db_Connection = new DatabaseConnection();
        System.out.println("Database Connection is Successful");
        try {
            Connection cn = db_Connection.getDBConnection();
            System.out.println("Connection Successful: ");
        } catch (SQLException e) {
            System.out.println("Connection Failed");
        }
    }
}
