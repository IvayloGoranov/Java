package accounts.loanAccounts;

import java.math.BigDecimal;
import java.time.LocalDate;

import accounts.Account;
import accounts.AccountType;
import customers.Customer;

public abstract class LoanAccount extends Account {
	
    private static final BigDecimal InterestFreePeriodForIndividuals = new BigDecimal("3");
    private static final BigDecimal InterestFreePeriodForCompanies = new BigDecimal("2");
    
    private LoanType loanType;
    
    public LoanAccount(Customer holder, BigDecimal balance, BigDecimal interestRate, LoanType loanType, AccountType accountType) {
    	
    	super(holder, balance, interestRate, accountType);
    	this.setLoanType(loanType);
    }

    public LoanType getLoanType() {
		
    	return this.loanType;
	}
    
	public void setLoanType(LoanType loanType) {
		
		this.loanType = loanType;
	}

	public LocalDate getEndDate() {
		
		return this.getStartdate().plusMonths(this.loanType.getNumValue());
	}
    
	@Override
	public BigDecimal calculateInterest(int months) {
		
		if (months < 0) {
        	
            throw new IllegalArgumentException("Interest number of months value cannot be negative");
        }

		BigDecimal interest = new BigDecimal("0");
        if (this.getAccountType() == AccountType.PERSONAL) {
        	
            if ((new BigDecimal(months).subtract(InterestFreePeriodForIndividuals)).compareTo(BigDecimal.ZERO) > 0) {
            	
                interest = this.getBalance().multiply(new BigDecimal("1").
                		add(this.getInterestRate().multiply(new BigDecimal(months).subtract(InterestFreePeriodForIndividuals))));
            }
        }
        
        if (this.getAccountType() == AccountType.COMPANY) {
        	
            if ((new BigDecimal(months).subtract(InterestFreePeriodForIndividuals)).compareTo(BigDecimal.ZERO) > 0) {
            	
            	interest = this.getBalance().multiply(new BigDecimal("1").
                		add(this.getInterestRate().multiply(new BigDecimal(months).subtract(InterestFreePeriodForCompanies))));
            }
        }
        
        return interest;
    }
}
