package org.hahunavth.springcore.ioccontainer.javabased;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;

@Configuration
@EnableAspectJAutoProxy
public class AccountConfiguration {
    @Bean
    @Lazy
    public AccountService accountService() {
        return new AccountService(accountRepository());
    }

    @Bean
    public AccountRepository accountRepository() {
        return new AccountRepository();
    }
}
