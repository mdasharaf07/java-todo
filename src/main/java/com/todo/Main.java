package com.todo;

import com.todo.gui.TodoAppGUI;
import com.todo.util.DatabaseConnection;
import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db_Connection = new DatabaseConnection();
        System.out.println("Database Connection is Successful");
        try {
            db_Connection.getDBConnection();
            System.out.println("Connection Successful: ");
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            System.exit(1);
        }

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
            System.err.println("Could not set look and feel: " + e.getMessage());

    }
    SwingUtilities.invokeLater(
        () -> {
            try{
                new TodoAppGUI().setVisible(true);
            }
            catch(Exception e){
                System.err.println("Error starting the Application: " + e.getLocalizedMessage());
            }
        }
    );

}
}