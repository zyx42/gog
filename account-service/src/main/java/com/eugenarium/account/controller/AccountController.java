package com.eugenarium.account.controller;

import com.eugenarium.account.persistence.domain.Account;
import com.eugenarium.account.persistence.domain.User;
import com.eugenarium.account.persistence.service.AccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PreAuthorize("#oauth2.hasScope('server') or #name.equals('demo')")
    @GetMapping(path = "/{name}")
    public Account getAccountByName(@PathVariable String name) {
        return accountService.findByName(name);
    }

    @GetMapping(path = "/current")
    public Account getCurrentAccount(Principal principal) {
        return accountService.findByName(principal.getName());
    }

    @PutMapping(path = "/current")
    public void saveCurrentAccount(Principal principal, @Valid @RequestBody Account account) {
        accountService.saveChanges(principal.getName(), account);
    }

    @PostMapping(path = "/")
    public Account createNewAccount(@Valid @RequestBody User user) {
        return accountService.create(user);
    }
}
