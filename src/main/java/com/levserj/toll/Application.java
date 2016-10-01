package com.levserj.toll;


import com.levserj.toll.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Serhii Levchynskyi on 01.10.2016.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private UserRepo repo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
