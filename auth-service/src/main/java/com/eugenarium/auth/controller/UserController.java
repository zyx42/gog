package com.eugenarium.auth.controller;

import com.eugenarium.auth.persistence.domain.User;
import com.eugenarium.auth.persistence.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @PostMapping(value = "/createUser")
    public void createUser(@Valid @RequestBody User user) {
        userService.create(user);
    }
}
