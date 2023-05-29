package com.example.endofterm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.endofterm.mapper")
public class EndOfTermApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndOfTermApplication.class, args);
    }

}
