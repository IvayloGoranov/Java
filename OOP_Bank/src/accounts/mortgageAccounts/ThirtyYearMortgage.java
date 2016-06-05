package accounts.mortgageAccounts;

import java.math.BigDecimal;

import accounts.AccountType;
import customers.Customer;

public class ThirtyYearMortgage extends MortgageAccount {

	public ThirtyYearMortgage(Customer holder, BigDecimal balance, BigDecimal interestRate, AccountType accountType,
			MortgageType mortgageType) {
		
		super(holder, balance, interestRate, accountType, mortgageType);
	}

}
