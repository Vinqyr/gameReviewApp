package edu.project.gamereviewapp.Repository;

import edu.project.gamereviewapp.Entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepositoryTest;

    @Test
    void name() {
        //Given
        Account account = new Account(1L, "Joe", "Doe");
        //When
        accountRepositoryTest.save(account);
        //Then


    }
}