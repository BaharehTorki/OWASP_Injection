package com.example.insecureInjectionApp.repository;

import com.example.insecureInjectionApp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsecureRepo {

    public Connection getConnection() {
        // Database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/owaspdb";
        String username = "root";
        String password = "root";

        // Step 1: Load the JDBC driver (this step is not required in JDBC 4.0+)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Step 2: Establish the connection
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            if (connection != null) {
                System.out.println("Connection established");
                return connection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> runQuery(String query) {
        List<User> result = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                result.add(User.builder()
                        .id(Long.valueOf(resultSet.getInt("age")))
                        .username(resultSet.getString("name"))
                        .build());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
