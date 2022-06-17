package com.tutego.date4u;

import com.tutego.date4u.entities.Profile;
import com.tutego.date4u.entities.Unicorn;
import com.tutego.date4u.repositories.ProfileRepository;
import com.tutego.date4u.repositories.UnicornRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ImportResource({"classpath*:applicationContext.xml"})
@SpringBootApplication
@EnableJpaAuditing
public class Date4uApplication {

    public static void main(String[] args) {
        SpringApplication.run(Date4uApplication.class, args);
    }

}