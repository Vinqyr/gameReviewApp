package edu.project.gamereviewapp.Service.account.AccountRegistrationService;

import edu.project.gamereviewapp.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRegistrationService {


    private final AccountRepository accountRepository;


    @Autowired
    public AccountRegistrationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void registerNewAccount(AccountRegistrationRequest request){

    }
}
