package me.hanum.springdata11;

import me.hanum.springdata11.account.Account;
import me.hanum.springdata11.account.AccountRepository;
import me.hanum.springdata11.account.Role;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Neo4jRunner implements ApplicationRunner {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account account = new Account();
        account.setEmail("hanum@mail.com");
        account.setUsername("hanum");

        Role role = new Role();
        role.setName("admin");

        account.getRoles().add(role);

        accountRepository.save(account);
    }
}
