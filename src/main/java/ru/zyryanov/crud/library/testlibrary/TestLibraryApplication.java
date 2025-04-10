package ru.zyryanov.crud.library.testlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TestLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestLibraryApplication.class, args);
    }

}
