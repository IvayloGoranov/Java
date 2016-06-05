package accounts.depositAccounts;

import java.math.BigDecimal;

import accounts.AccountType;
import customers.Customer;

public class SixMonthDeposit extends DepositAccount {

	public SixMonthDeposit(Customer holder, BigDecimal balance, BigDecimal interestRate, DepositType depositType,
			AccountType accountType) {
		
		super(holder, balance, interestRate, depositType, accountType);
	}

}
