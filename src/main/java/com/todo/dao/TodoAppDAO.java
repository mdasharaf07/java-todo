package com.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.todo.model.Todo;
import com.todo.util.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;

public class TodoAppDAO {

    private static final String SELECT_ALL_TODOS = "SELECT * FROM todos ORDER BY created_at DESC";
    private static final String INSERT_TODO = "INSERT INTO todos (title, description, completed, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_TODO_BY_ID = "SELECT * FROM todos WHERE id=?";
    private static final String UPDATE_TODO = "UPDATE todos SET title=?, description=?, completed=?, updated_at=? WHERE id=?";
    private static final String DELETE_TODO = "DELETE FROM todos WHERE id=?";



    // Create a new todo

    public int createtodo(Todo todo)throws SQLException
    {
        try(
            Connection conn = new DatabaseConnection().getDBConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_TODO, Statement.RETURN_GENERATED_KEYS);
        )
        {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setBoolean(3, todo.isCompleted());
            stmt.setObject(4, todo.getCreated_at());
            stmt.setObject(5, todo.getUpdated_at());
            
            int rowAffected = stmt.executeUpdate();

            if (rowAffected == 0){
                throw new SQLException("Creating todo failed, no rows is inserted.");
            }

            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return generatedKeys.getInt(1);
                }
                else{
                    throw new SQLException("Creating todo failed, no ID obtained.");
                }
            }
        }
    }

    public Todo getTodoById(int todoId) throws SQLException{
        try(
            Connection conn = new DatabaseConnection().getDBConnection();
            PreparedStatement stmt = conn.prepareStatement(SELECT_TODO_BY_ID);
        )
        {
            stmt.setInt(1, todoId);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                return todoRow(res);
            }
        }
        return null;
    }

    public boolean updateTodo(Todo todo) throws SQLException{
        try(
            Connection conn = new DatabaseConnection().getDBConnection();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_TODO);
        )
        {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setBoolean(3, todo.isCompleted());
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(5, todo.getId());
            int rowAffected = stmt.executeUpdate();
            return rowAffected > 0;
        }
    }

    public boolean deleteTodo(int todoId) throws SQLException{
        try(
            Connection conn = new DatabaseConnection().getDBConnection();
            PreparedStatement stmt = conn.prepareStatement(DELETE_TODO);
        )
        {
            stmt.setInt(1, todoId);
            int rowAffected = stmt.executeUpdate();
            return rowAffected >0;
            
        }
    }

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
            PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_TODOS);
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
