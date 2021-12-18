package anony.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import anony.entity.User;
import anony.repository.UserRepository;

@Component
public class InitDbRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitDbRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        insertAdmin();
    }

    private void insertAdmin() {
        userRepository.findByUsername("admin").ifPresentOrElse(user -> {}, () -> {
            var user = new User("admin", passwordEncoder.encode("123456"), true);
            userRepository.save(user);
        });
    }
}
