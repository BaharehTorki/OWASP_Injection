package com.example.secureInjectionApp.controller;

import com.example.secureInjectionApp.model.User;
import com.example.secureInjectionApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/form")
    public String showForm() {
        return "form"; // The name of your HTML form template (form.html)
    }

    @PostMapping("/submit")
    public String submitForm(@RequestParam String name, @RequestParam int age, Model model) {
        User user = User.builder().build();
        user.setName(name);
        user.setAge(age);
        userRepository.save(user);

        model.addAttribute("message", "User data saved successfully!");
        return "result"; // The name of your result template (result.html)
    }
}
