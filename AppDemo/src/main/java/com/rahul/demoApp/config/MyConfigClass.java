package com.rahul.demoApp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigClass {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
