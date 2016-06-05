package accounts.loanAccounts;

import java.math.BigDecimal;

import accounts.AccountType;
import customers.Customer;

public class SixMonthLoan extends LoanAccount {

	public SixMonthLoan(Customer holder, BigDecimal balance, BigDecimal interestRate, LoanType loanType,
			AccountType accountType) {
		
		super(holder, balance, interestRate, loanType, accountType);
	}

}
