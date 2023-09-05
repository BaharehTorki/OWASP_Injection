package com.example.insecureInjectionApp.repository;

import com.example.insecureInjectionApp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SecureRepo {

    public List<User> retrieveAllByName(String name) {

        String baseQuery = "select * from user where name = ?";
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
                System.out.println("-----------------Connection established-----------------");
                System.out.println("\t\t\tRunning a query that make SECURE INJECTION.");
                System.out.println("\t\t\t" + baseQuery + ", ? is prepared with "+ name);

                PreparedStatement preparedStatement = connection.prepareStatement(baseQuery);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    result.add(User.builder()
                            .age(resultSet.getInt("age"))
                            .username(resultSet.getString("name"))
                            .password(resultSet.getString("password"))
                            .build());
                }
                resultSet.close();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\t\t=================  RESULT =================");
        return result;
    }
}
