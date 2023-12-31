package com.example.insecureInjectionApp.repository;

import com.example.insecureInjectionApp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsecureRepo {

    public List<User> retrieveAllByName(String name) {

        String baseQuery = "select * from insecure_user where name=";
        // Database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/owaspdb";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<User> result = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            if (connection != null) {
                System.out.println("----------------- Connection established -----------------");
                System.out.println("\t\t\tRunning a query with INSECURE INJECTION.");
                System.out.println("\t\t\t"+ baseQuery+name);

                Statement statement = connection.createStatement();
                System.out.println(" ");
                ResultSet resultSet = statement.executeQuery(baseQuery + name);
                while (resultSet.next()) {
                    result.add(User.builder()
                            .age(resultSet.getInt("age"))
                            .username(resultSet.getString("name"))
                            .password(resultSet.getString("password"))
                            .build());
                }
                resultSet.close();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\t\t=================  RESULT =================");
        return result;
    }
}
