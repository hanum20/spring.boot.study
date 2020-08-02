package me.hanum.springsecurity02;

import me.hanum.springsecurity02.account.Account;
import me.hanum.springsecurity02.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account hanum = accountService.createAccount("hanum", "1234");
        System.out.println(hanum.toString());
    }
}
