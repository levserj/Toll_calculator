package com.levserj.toll;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Serhii Levchynskyi on 01.10.2016.
 */
@SpringBootApplication()
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        log.info("In the Application Config");
        SpringApplication.run(Application.class, args);
    }
}
