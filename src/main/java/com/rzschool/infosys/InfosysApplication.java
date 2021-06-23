package com.rzschool.infosys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class InfosysApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfosysApplication.class, args);
    }

}
