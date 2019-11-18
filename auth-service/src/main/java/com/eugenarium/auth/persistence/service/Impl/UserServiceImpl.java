package com.eugenarium.auth.persistence.service.Impl;

import com.eugenarium.auth.persistence.domain.User;
import com.eugenarium.auth.persistence.repository.UserRepository;
import com.eugenarium.auth.persistence.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {

        Optional<User> existing = repository.findById(user.getUsername());
        existing.ifPresent(it -> {throw new IllegalArgumentException("user already exists: " + it.getUsername());});

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        repository.save(user);

        LOGGER.info("new user has been created: {}", user.getUsername());
    }
}
