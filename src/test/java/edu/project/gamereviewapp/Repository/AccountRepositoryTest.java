package edu.project.gamereviewapp.Repository;

import edu.project.gamereviewapp.Entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepositoryTest;

    @Test
    void name() {
        //Given
        Account account = new Account(1L, "Joe", "Doe","2211");
        //When
        accountRepositoryTest.save(account);
        //Then
        Optional<Account> optionalAccount = accountRepositoryTest.findById(account.getId());
        assertThat(optionalAccount).isPresent().hasValueSatisfying(it -> assertThat(it).isEqualTo(account));
    }

    @Test
    void namae() {
        //Given
        Long id = 1L;
        Account account = new Account(1L,"Joe","Lol","2211");
        //When
        accountRepositoryTest.findById(id);
        accountRepositoryTest.save(account);
        //Then
        Optional<Account> optionalAccount = accountRepositoryTest.findById(id);
        assertThat(optionalAccount).isPresent().hasValueSatisfying(it ->{
//            assertThat(it.getId()).isEqualTo(id);
//            assertThat(it.getLastName()).isEqualTo("Lol");
//            assertThat(it.getName()).isEqualTo("Joe");
            assertThat(it).isEqualToComparingFieldByField(account);
            ;});
    }
}