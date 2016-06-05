package accounts.depositAccounts;

import java.math.BigDecimal;
import java.time.LocalDate;

import accounts.Account;
import accounts.AccountType;
import customers.Customer;
import interfaces.IWithdrawMoney;

public abstract class DepositAccount extends Account implements IWithdrawMoney {
	
    private DepositType depositType;
	
	public DepositAccount(Customer holder, BigDecimal balance, BigDecimal interestRate, 
			DepositType depositType, AccountType accountType) {
    	
    	super(holder, balance, interestRate, accountType);
        this.setDepositType(depositType);
    }

    public DepositType getDepositType() {
		
    	return this.depositType;
	}
    
	public void setDepositType(DepositType depositType) {
		
		this.depositType = depositType;
	}

	public LocalDate getEndDate() {
		
		return this.getStartdate().plusMonths(this.depositType.getNumValue());
	}

	@Override
	public void withdrawMoney(BigDecimal amount) {
		
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
        	
            throw new IllegalArgumentException("Withdrawal amount cannot be negative.");
        }
        
        if (amount.compareTo(this.getBalance()) > 0)  {
        	
            throw new IllegalArgumentException("Withdrawal amount cannot be bigger than current balance.");
        }
        
        this.setBalance(this.getBalance().subtract(amount));
    }
	
	@Override
    public BigDecimal calculateInterest(int months) {
    	
        if (months < 0) {
        	
            throw new IllegalArgumentException("Interest number of months value cannot be negative");
        }
        
        BigDecimal interest = new BigDecimal("0");
        if (this.getBalance().compareTo(new BigDecimal("1000")) < 0) {
        	
            return interest;
        }
        
        interest = this.getBalance().multiply(new BigDecimal("1").
        		add(this.getInterestRate().multiply(new BigDecimal(months))));
        
        return interest;
    }
}
