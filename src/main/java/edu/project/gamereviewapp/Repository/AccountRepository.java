package edu.project.gamereviewapp.Repository;

import edu.project.gamereviewapp.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {


}
