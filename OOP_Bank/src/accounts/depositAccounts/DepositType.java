package accounts.depositAccounts;

public enum DepositType {
	
    SIXMONTH(6),
    TWELVEMONTH(12), 
    TWENTYFOURMONTH(24);
	
	private int numValue;
	
	private DepositType(int numValue) {
		
		this.numValue = numValue;
	}

	public int getNumValue() {
		
		return this.numValue;
	}
}
