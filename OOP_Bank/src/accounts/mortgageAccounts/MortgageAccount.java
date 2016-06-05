package accounts.mortgageAccounts;

import java.math.BigDecimal;
import java.time.LocalDate;

import accounts.Account;
import accounts.AccountType;
import customers.Customer;

public abstract class MortgageAccount extends Account {
	
    private static final int InterestFreePeriodForIndividuals = 6;
    private static final int InterestBonusPeriodForCompanies = 12;
    
    private MortgageType mortgageType;
    
    public MortgageAccount(Customer holder, BigDecimal balance, BigDecimal interestRate, AccountType accountType, MortgageType mortgageType) {
    	
    	super (holder, balance, interestRate, accountType);
        this.setMortgageType(mortgageType);
    }

    public MortgageType getMortgageType() {
		
    	return this.mortgageType;
	}
    
	public void setMortgageType(MortgageType mortgageType) {
		
		this.mortgageType = mortgageType;
	}

	public LocalDate getEndDate() {
		
		return this.getStartdate().plusMonths(this.mortgageType.getNumValue());
	}
	
	@Override
    public BigDecimal calculateInterest(int months) {
		
		if (months < 0) {
        	
            throw new IllegalArgumentException("Interest number of months value cannot be negative");
        }
		
        BigDecimal interest = new BigDecimal("0");
        
        if (this.getAccountType() == AccountType.PERSONAL) {
        	
            if (months - InterestFreePeriodForIndividuals > 0) {
            	
                interest = this.getBalance().multiply(new BigDecimal("1").
                		add(this.getInterestRate().multiply(new BigDecimal(months - InterestFreePeriodForIndividuals))));
            }
        }
        
        if (this.getAccountType() == AccountType.COMPANY) {
        	
            if (months - InterestBonusPeriodForCompanies > 0) {
            	
                BigDecimal bonusInterest = this.getBalance().multiply(this.getInterestRate().
                		multiply(new BigDecimal(InterestBonusPeriodForCompanies)));
                bonusInterest = bonusInterest.divide(new BigDecimal("2"));
                interest = bonusInterest.add(this.getBalance().multiply(
                		new BigDecimal("1").add(this.getBalance().multiply(new BigDecimal(months - InterestBonusPeriodForCompanies)))));
            } else {
            	
                interest = this.getBalance().multiply(new BigDecimal("1").
                		add(this.getInterestRate().multiply(new BigDecimal(months))));
                interest = interest.divide(new BigDecimal("2"));
            }
        }
        
        return interest;
    }
}
