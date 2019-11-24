package com.eugenarium.statistics.controller;

import com.eugenarium.statistics.persistence.domain.Account;
import com.eugenarium.statistics.persistence.domain.statistics.DataPoint;
import com.eugenarium.statistics.persistence.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping(value = "/current")
    public List<DataPoint> getCurrentAccountStatistics(Principal principal) {
        return statisticsService.findByAccountName(principal.getName());
    }

    @PreAuthorize("#oauth2.hasScope('server') or #accountName.equals('demo')")
    @GetMapping(value = "/{accountName}")
    public List<DataPoint> getStatisticsByAccountName(@PathVariable String accountName) {
        return statisticsService.findByAccountName(accountName);
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @PutMapping(value = "/{accountName}")
    public void saveAccountStatistics(@PathVariable String accountName, @Valid @RequestBody Account account) {
        statisticsService.save(accountName, account);
    }
}