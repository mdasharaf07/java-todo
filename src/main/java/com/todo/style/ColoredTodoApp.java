// package com.todo.style;

// import javax.swing.*;
// import java.awt.*;
// import javax.swing.table.DefaultTableModel;

// public class ColoredTodoApp extends javax.swing.JFrame {
//     private JTable todoTable;
//     private DefaultTableModel tableModel;
//     private JTextField titleField;
//     private JTextArea descriptionArea;
//     private JCheckBox completedCheckBox;


//     public ColoredTodoApp(){
//         setTitle("Java Todo App");
//         setSize(600,400);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//         JPanel mainPanel = new JPanel(new BorderLayout());
//         mainPanel.setBackground(new Color(45, 52, 54));

//         tableModel = new DefaultTableModel(new String[]{"Title", "Description", "Completed"}, 0);
//         todoTable = new JTable(tableModel);
//         todoTable.setBackground(new Color(223, 230, 233));
//         todoTable.setForeground(Color.BLACK);
//         todoTable.setFont(new Font("Arial", Font.PLAIN, 14));
//         todoTable.setRowHeight(25);

//         JScrollPane scrollPane = new JScrollPane(todoTable);

//         JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
//         inputPanel.setBackground(new Color(99, 110, 114));

//         JLabel titleLabel = new JLabel("Title:");
//         titleLabel.setForeground(Color.WHITE);
//         titleField = new JTextField();

//         JLabel descLabel = new JLabel("Description:");
//         descLabel.setForeground(Color.WHITE);
//         descriptionArea = new JTextArea(3, 20);

//         completedCheckBox = new JCheckBox("Completed");
//         completedCheckBox.setBackground(new Color(99, 110, 114));
//         completedCheckBox.setForeground(Color.WHITE);

//         inputPanel.add(titleLabel);
//         inputPanel.add(titleField);
//         inputPanel.add(descLabel);
//         inputPanel.add(new JScrollPane(descriptionArea));
//         inputPanel.add(new JLabel(""));
//         inputPanel.add(completedCheckBox);
//         JPanel buttonPanel = new JPanel();
//         buttonPanel.setBackground(new Color(45, 52, 54));

//         JButton addButton = new JButton("Add Task");
//         addButton.setBackground(new Color(39, 174, 96));
//         addButton.setForeground(Color.WHITE);

//         JButton deleteButton = new JButton("Delete Task");
//         deleteButton.setBackground(new Color(192, 57, 43));
//         deleteButton.setForeground(Color.WHITE);

//         buttonPanel.add(addButton);
//         buttonPanel.add(deleteButton);

//         mainPanel.add(scrollPane, BorderLayout.CENTER);
//         mainPanel.add(inputPanel, BorderLayout.NORTH);
//         mainPanel.add(buttonPanel, BorderLayout.SOUTH);

//         this.add(mainPanel);
//         this.setVisible(true);
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(ColoredTodoApp::new);
//     }
// }
