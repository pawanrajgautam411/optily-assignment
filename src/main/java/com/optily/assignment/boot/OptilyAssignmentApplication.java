package com.optily.assignment.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 */
@ComponentScan({"com.optily.assignment.boot",
        "com.optily.assignment.controller"})
@SpringBootApplication
public class OptilyAssignmentApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(OptilyAssignmentApplication.class, args);
    }

}
