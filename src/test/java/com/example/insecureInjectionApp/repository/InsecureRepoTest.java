package com.example.insecureInjectionApp.repository;

import com.example.insecureInjectionApp.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsecureRepoTest {

    InsecureRepo sut = new InsecureRepo();

    @Test
    void should_get_correct_connection() {
        Connection actual = sut.getConnection();
        assertNotNull(actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"azad", "adel"})
    void should_retrieve_data_from_database(String name) {
        List<User> users = sut.runQuery("select * from user where name = " + name);
        users.forEach(System.out::println);
    }
}