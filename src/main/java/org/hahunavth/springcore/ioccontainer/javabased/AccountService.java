package org.hahunavth.springcore.ioccontainer.javabased;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class AccountService {
    @Getter
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MessageSource messageSource;

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String getAccountName() {
        return messageSource.getMessage("account.name", null, Locale.ENGLISH);
    }
}
