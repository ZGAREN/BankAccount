package org.sid.bank.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bank.ennums.AccountStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
//lorsqu'elle s'agit de l'heritage
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4,discriminatorType = DiscriminatorType.STRING)
@Data @AllArgsConstructor @NoArgsConstructor
public abstract class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAT;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "banKAccount",fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;
}

