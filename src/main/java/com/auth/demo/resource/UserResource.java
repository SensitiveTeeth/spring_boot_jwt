package com.auth.demo.resource;

import com.auth.demo.entity.User;
import com.auth.demo.repository.UserRepository;
import com.auth.demo.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Optional;

@RestController
public class UserResource {
    private UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/test/user/{id}")
    public String accessUserContent(HttpServletRequest request, @PathVariable Long id) throws Exception {
        try {
            final String token = request.getHeader("authorization");
            if (!JWTUtils.verifyToken(token)) throw new Exception("Invalid Token");
            System.out.println(request);

            Optional<User> user = this.userRepository.findById(id);
            if (user.isEmpty()) throw new Exception("User Not found");
            return user.toString();
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody User requestUser) throws Exception {
        try {
            String username = requestUser.getUsername();
            Optional<User> user = this.userRepository.findByUsername(username);
            if (user.isEmpty()) {
                throw new Exception("User Not found");
            } else {
                String password = requestUser.getPassword();
                User actualUser = user.get();
                String userPassword = actualUser.getPassword();
                PasswordEncoder passwordEncoder =
                        PasswordEncoderFactories.createDelegatingPasswordEncoder();

                if (passwordEncoder.matches(password, userPassword)) {
                    return JWTUtils.createToken(actualUser);
                }
            }
            return "login failed";
        } catch (Exception e) {
            System.out.println(e);
            return "login failed";
        }
    }

    @PostMapping("/auth/signup")
    public String signup(@RequestBody User user) throws Exception {
        try {
            PasswordEncoder passwordEncoder =
                    PasswordEncoderFactories.createDelegatingPasswordEncoder();

            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            this.userRepository.save(user);
            return user.getUsername();
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
    }
}
