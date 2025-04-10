package com.empresa.msusuarios.seed;

import com.empresa.msusuarios.model.User;
import com.empresa.msusuarios.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            for (int i = 1; i <= 10; i++) {
                User user = new User();
                user.setUsername("usuario" + i);
                user.setPassword("password" + i);
                userRepository.save(user);
            }
            System.out.println("✅ 10 usuarios ficticios creados.");
        } else {
            System.out.println("ℹ️ Usuarios ya existentes, no se creó ningún dato nuevo.");
        }
    }
}
