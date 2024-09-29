package com.project.mentor_booking;

import org.modelmapper.ModelMapper;
import com.project.controller.*;
import com.project.service.*;
import com.project.repository.*;
import com.project.model.*;
import lombok.Data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.project.controller", "com.project.service"})
@EnableJpaRepositories(basePackages = "com.project.repository")
@EntityScan(basePackages = "com.project.model")
public class MentorBookingApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(MentorBookingApplication.class, args);
    }

}
