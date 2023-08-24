package com.example.CryptographicFailures;

public class User {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    // Storing passwords in plain text is insecure
    public void setPassword(String password) {
        this.password = password;
    }

    // This will store the hashed password
    /*public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }*/
}
