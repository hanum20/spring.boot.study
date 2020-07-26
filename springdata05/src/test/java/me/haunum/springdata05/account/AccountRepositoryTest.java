package me.haunum.springdata05.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest // Repository 관련된 bean만 가져와서 테스트(슬라이싱 테스트)
// @SpringBootTest는 통합테스트이다. @SpringBoot
public class AccountRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws Exception {
        try(Connection connection = dataSource.getConnection()){
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getURL());
            System.out.println(metaData.getDriverName());
            System.out.println(metaData.getUserName());
        }
    }

    @Test
    public void account() throws Exception {
        Account account = new Account();
        account.setPassword("pass");
        account.setUsername("hanum");

        Account newAccount = accountRepository.save(account);

        assertThat(newAccount).isNotNull();

        Account existingAccount = accountRepository.findByUsername(newAccount.getUsername());
        assertThat(existingAccount).isNotNull();

        // null
        //Account nonExistionAccount = accountRepository.findByUsername("hhhh");
        //assertThat(nonExistionAccount).isNotNull();

    }
}