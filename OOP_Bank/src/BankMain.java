import java.math.BigDecimal;

import accounts.AccountType;
import accounts.mortgageAccounts.MortgageAccount;
import accounts.mortgageAccounts.MortgageType;
import accounts.mortgageAccounts.ThirtyYearMortgage;
import customers.CompanyCustomer;
import customers.Customer;

public class BankMain {

	public static void main(String[] args) {
		
		Customer tsarvulInvestment = new CompanyCustomer("Tsarvul Investment");
        MortgageAccount mortgage = new ThirtyYearMortgage
            (tsarvulInvestment, new BigDecimal(10000), new BigDecimal(3.2), AccountType.COMPANY, MortgageType.THIRTYYEAR);
        BigDecimal interest = mortgage.calculateInterest(12);
        System.out.println(interest.setScale(2, 2));
	}
}
