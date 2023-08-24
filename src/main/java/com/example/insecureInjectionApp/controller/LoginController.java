package com.example.insecureInjectionApp.controller;

import ch.qos.logback.core.model.Model;
import com.example.insecureInjectionApp.model.User;
import com.example.insecureInjectionApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username, @RequestParam String password, Model model) {

        // Osäker kod: direkt infogning av parametrar i SQL-fråga
        /*List<User> users = userRepository.getAllUsers();
        String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
*/
        // Säker kod: använd parametriserad fråga
        List<User> users = userRepository.getUserByUsernameAndPassword(username, password);

        return "login";
    }
}
