package com.safetynet.alerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class AlertsApplication {

    /**
     * Main method to launch the application
     *
     * @param args - Default parameter
     */
    public static void main(String[] args) {
        SpringApplication.run(AlertsApplication.class, args);
    }

}
