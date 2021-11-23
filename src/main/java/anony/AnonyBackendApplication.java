package anony;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import anony.entity.User;
import anony.repository.UserRepository;

@SpringBootApplication
public class AnonyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnonyBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(UserRepository repository) {
        return (var args) -> {
            repository.save(new User("test", "123456", LocalDateTime.now()));
        };
    }

}
