package org.sid.bank.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bank.ennums.AccountStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class BankAccount {
    @Id
    private Long id;
    private double balance;
    private Date createdAT;
    private AccountStatus status;
    @ManyToOne
    private Customer customer;

    private List<AccountOperation> accountOperations;
}

