package com.blogapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAppApiApplication.class, args);
    }

    //ModelMapper library is used to map objects from one class model to another
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
