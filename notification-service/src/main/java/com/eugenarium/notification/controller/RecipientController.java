package com.eugenarium.notification.controller;

import com.eugenarium.notification.persistence.domain.Recipient;
import com.eugenarium.notification.persistence.service.RecipientService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/recipients")
public class RecipientController {

    private final RecipientService recipientService;

    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @GetMapping(path = "/current")
    public Object getCurrentNotificationSettings(Principal principal) {
        return recipientService.findByAccountName(principal.getName());
    }

    @PutMapping(path = "/current")
    public Object saveCurrentNotificationsSettings(Principal principal, @Valid @RequestBody Recipient recipient) {
        return recipientService.save(principal.getName(), recipient);
    }
}
