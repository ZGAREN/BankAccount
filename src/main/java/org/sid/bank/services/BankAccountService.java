package org.sid.bank.services;

import org.sid.bank.entities.BankAccount;
import org.sid.bank.entities.Customer;
import org.sid.bank.exceptions.BalanceNotSufficentException;
import org.sid.bank.exceptions.BankAccountNotFoundException;
import org.sid.bank.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {

     Customer saveCustomer(Customer customer);
     BankAccount currrentAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;

     BankAccount savingAccount(double initialBalance, double interestRate, Long customerId)throws CustomerNotFoundException;
     List<Customer> listCustomers ();
     BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;
     void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficentException;
     void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
     void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficentException;
     public List<BankAccount> bankAccountList();
}
