package org.sid.bank;

import org.sid.bank.entities.*;
import org.sid.bank.exceptions.BalanceNotSufficentException;
import org.sid.bank.exceptions.BankAccountNotFoundException;
import org.sid.bank.exceptions.CustomerNotFoundException;
import org.sid.bank.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService){

		return args -> {
			Stream.of("Hassen","Imane","Mohamed").forEach(name->{
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gamil.com");
				bankAccountService.saveCustomer(customer);
			});
			bankAccountService.listCustomers().forEach(customer -> {
				try {
					bankAccountService.currrentAccount(Math.random()*9000,9000,customer.getId());
					bankAccountService.savingAccount(Math.random()*12000,5.5,customer.getId());
					List<BankAccount> bankAccounts =bankAccountService.bankAccountList();

					for (BankAccount bankAccount : bankAccounts){
						bankAccountService.credit(bankAccount.getId(), 10000+Math.random()*120000,"Credit");
						bankAccountService.debit(bankAccount.getId(), 1000+Math.random()*900,"Debit");
					}

				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				}
				catch (BankAccountNotFoundException | BalanceNotSufficentException e) {
					e.printStackTrace();
				}
			});
		};
	}
		/*return args ->{
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
					accountOperation.setDescription(OperationType.);
					accountOperation.setType(Math.random()>0.5? OperationType.DEBIT : OperationType.CREADIT);
					accountOperation.setBanKAccount(bankAccount);
					accountOperationRepository.save(accountOperation);
				}
			});
		};
	}*/
}
