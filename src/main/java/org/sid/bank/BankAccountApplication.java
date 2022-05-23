package org.sid.bank;

import org.sid.bank.ennums.AccountStatus;
import org.sid.bank.ennums.OperationType;
import org.sid.bank.entities.*;
import org.sid.bank.reposotories.AccountOperationRepository;
import org.sid.bank.reposotories.BankAccountRepository;
import org.sid.bank.reposotories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Currency;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
							AccountOperationRepository accountOperationRepository){
		return args ->{
			Stream.of("Hassen", "Yassine", "Aicha").forEach(name->{
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepository.save(customer);
			});
			customerRepository.findAll().forEach(customer -> {
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*90000);
				currentAccount.setCreatedAT(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(customer);
				currentAccount.setOverDraft(9000);
				bankAccountRepository.save(currentAccount);

				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*90000);
				savingAccount.setCreatedAT(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(customer);
				savingAccount.setInterestRate(5.5);
				bankAccountRepository.save(savingAccount);
			});
			bankAccountRepository.findAll().forEach(bankAccount -> {
				for (int i = 0; i < 10; i++) {
					AccountOperation accountOperation = new AccountOperation();
					accountOperation.setOperationDate(new Date());
					accountOperation.setAmount(Math.random()*12000);
					accountOperation.setType(Math.random()>0.5? OperationType.DEBIT : OperationType.CREADIT);
					accountOperation.setBanKAccount(bankAccount);
					accountOperationRepository.save(accountOperation);
				}
			});
		};

	}
}
