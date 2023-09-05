package com.example.insecureInjectionApp.repository;

import com.example.insecureInjectionApp.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public List<User> getUserByUsernameAndPassword(String username, String password) {
        return null;
    }

}
