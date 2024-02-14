package ru.springgb.clienttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("ru.springgb.cliententity.model")
public class ClientTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientTaskApplication.class, args);
    }

}
