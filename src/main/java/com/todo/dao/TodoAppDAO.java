package com.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.todo.model.Todo;
import com.todo.util.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;

public class TodoAppDAO {

    private Todo todoRow(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        boolean completed = rs.getBoolean("completed");
        LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();
        Todo todo = new Todo(id, title, description, completed, created_at, updated_at);
        return todo;

    }

    public List<Todo> getTodoRow() throws SQLDataException, java.sql.SQLException {
        List<Todo> todos = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        try(Connection conn = dbConnection.getDBConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos ORDER BY created_at DESC");
            ResultSet res = stmt.executeQuery();
        )
        {
            while(res.next()){
                todos.add(todoRow(res));
            }
        }
        return todos;

        }
}
