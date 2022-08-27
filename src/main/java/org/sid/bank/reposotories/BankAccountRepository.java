package org.sid.bank.reposotories;

import org.sid.bank.entities.BankAccount;
import org.sid.bank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
