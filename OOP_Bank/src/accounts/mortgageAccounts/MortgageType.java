package accounts.mortgageAccounts;

public enum MortgageType {
	
    THIRTYYEAR(360), 
    THIRTYFIVEYEAR(420);
	
	private int numValue;
	
	private MortgageType(int numValue) {
		
		this.numValue = numValue;
	}

	public int getNumValue() {
		
		return this.numValue;
	}
}
