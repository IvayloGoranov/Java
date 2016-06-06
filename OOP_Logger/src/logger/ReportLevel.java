package logger;

public enum ReportLevel {
	
    INFO(0),
    WARN(1),
    ERROR(2),
    CRITICAL(3),
    FATAL(4);

	private int numValue;
	
	private ReportLevel(int numValue) {
		
		this.numValue = numValue;
	}
	
	public int getNumValue() {
		
		return this.numValue;
	}	
}