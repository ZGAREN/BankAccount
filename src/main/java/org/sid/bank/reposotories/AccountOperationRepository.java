package org.sid.bank.reposotories;

import org.sid.bank.entities.AccountOperation;
import org.sid.bank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
}
