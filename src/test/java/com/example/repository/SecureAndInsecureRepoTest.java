package com.example.repository;

import com.example.insecureInjectionApp.model.User;
import com.example.insecureInjectionApp.repository.InsecureRepo;
import com.example.insecureInjectionApp.repository.SecureRepo;
import org.junit.jupiter.api.Test;

import java.util.List;

class SecureAndInsecureRepoTest {

    InsecureRepo inSecureSut = new InsecureRepo();
    SecureRepo secureSut = new SecureRepo();

    @Test
    void test_insecure_data_from_database() {
        List<User> users = inSecureSut.retrieveAllByName("name");
        if(users.size()==0) {
            System.out.println("\t\tNOTHING TO SHOW!");
        }else {
            users.forEach(item -> System.out.println("\t\t" + item));
        }
    }

    @Test
    void test_secure_data_from_database() {
        List<User> users = secureSut.retrieveAllByName("name");
        if(users.size()==0) {
            System.out.println("\t\tNOTHING TO SHOW!\n");
        }else {
            users.forEach(item -> System.out.println("\t\t" + item));
        }    }

}