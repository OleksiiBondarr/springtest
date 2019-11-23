package spring.mvc.roombooking.demo;


import jdk.internal.instrumentation.Logger;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            repository.save(new User("Bilbo Baggins", "burglar"));
            repository.save(new User("Frodo Baggins", "thief"));
        };

    }
}