/*
 * File Created: 2021/12/07 10:52:25
 * Author: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 * -----
 * Last Modified: 2021/12/07 10:55:27
 * Modified By: ZhengxuanQian (zhengxuanqian@smail.nju.edu.cn)
 */
package anony.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import anony.entity.User;
import anony.repository.UserRepository;

@Component
public class InitDbRunner implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public InitDbRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        insertAdmin();
    }

    private void insertAdmin() {
        var user = new User("admin", passwordEncoder.encode("123456"), true);
        userRepository.save(user);
    }
}
