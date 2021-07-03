package com.kmitl.web_programming_2_63_final;

import com.kmitl.web_programming_2_63_final.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class WebProgramming263FinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebProgramming263FinalApplication.class, args);
    }


}
