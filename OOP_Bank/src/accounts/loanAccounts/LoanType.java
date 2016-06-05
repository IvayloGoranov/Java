package accounts.loanAccounts;

public enum LoanType
{
    SIXMONTH(6),
    TWELVEMONTH(12);
	
	private int numValue;
	
	private LoanType(int numValue) {
		
		this.numValue = numValue;
	}

	public int getNumValue() {
		
		return this.numValue;
	}
}
