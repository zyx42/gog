package com.eugenarium.account.persistence.service.Impl;

import com.eugenarium.account.client.AuthServiceClient;
import com.eugenarium.account.client.StatisticsServiceClient;
import com.eugenarium.account.persistence.domain.Account;
import com.eugenarium.account.persistence.domain.User;
import com.eugenarium.account.persistence.repository.AccountRepository;
import com.eugenarium.account.persistence.service.AccountService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.springframework.util.Assert;

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final StatisticsServiceClient statisticsClient;

    private final AuthServiceClient authClient;

    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(StatisticsServiceClient statisticsClient, AuthServiceClient authClient, AccountRepository repository) {
        this.statisticsClient = statisticsClient;
        this.authClient = authClient;
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account findByName(String accountName) {
        Assert.hasLength(accountName);
        return repository.findByName(accountName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account create(User user) {

        Account existing = repository.findByName(user.getUsername());
        Assert.isNull(existing, "account already exists: " + user.getUsername());

        authClient.createUser(user);

        // TO-DO - add exercises for the user
        //
        //

        Account account = new Account();
        // Set account fields here
        //
        //

        repository.save(account);

        LOGGER.info("new account has been created: " + account.getName());

        return account;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveChanges(String name, Account update) {

        Account account = repository.findByName(name);
        Assert.notNull(account, "can't find an account with name " + name);

        // Updating account fields here
        //
        //
        //
        repository.save(account);

        LOGGER.debug("account {} changes has been saved", name);

        statisticsClient.updateStatistics(name, account);
    }
}
