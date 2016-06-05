package accounts;

import java.math.BigDecimal;
import java.time.LocalDate;

import customers.Customer;
import interfaces.ICalculateInterest;
import interfaces.IDepositMoney;

public abstract class Account implements IDepositMoney, ICalculateInterest {
	
    private BigDecimal interestRate;
    private BigDecimal balance;
    private LocalDate startDate;
    private AccountType accountType;
    private Customer holder;
    
    public Account(Customer holder, BigDecimal balance, BigDecimal interestRate, AccountType accountType) {
    	
        this.holder = holder;
        this.setBalance(balance);
        this.setInterestRate(interestRate);
        this.startDate = LocalDate.now();
        this.setAccountType(accountType);
    }
    
    public AccountType getAccountType() {
		
    	return this.accountType;
	}
    
	public void setAccountType(AccountType accountType) {
		
		this.accountType = accountType;
	}

    public Customer getHolder() {
		
    	return this.holder;
	}

    public LocalDate getStartdate() {
		
    	return this.startDate;
	}

	public BigDecimal getBalance() {
		
		return this.balance;
	}

	public void setBalance(BigDecimal value) {
		
		if (value.compareTo(BigDecimal.ZERO) < 0) {
			
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
		
        this.balance = value;
	}

	public BigDecimal getInterestRate() {
		
		return this.interestRate.divide(new BigDecimal("100"));
	}

	public void setInterestRate(BigDecimal value) {
		
		if (value.compareTo(BigDecimal.ZERO) < 0) {
			
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
		
        this.interestRate = value;
	}

	public void depositMoney(BigDecimal amount) {
		
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
        	
            throw new IllegalArgumentException("Deposit amount cannot be negative.");
        }
        
        this.setBalance(this.getBalance().add(amount));
    }
	
    public abstract BigDecimal calculateInterest(int months);
}
