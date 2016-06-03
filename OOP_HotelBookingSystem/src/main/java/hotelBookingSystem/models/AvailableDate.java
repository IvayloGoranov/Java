package hotelBookingSystem.models;

import java.time.LocalDate;

public class AvailableDate {

	private LocalDate startDate;
	private LocalDate endDate;
	
	public AvailableDate(LocalDate startDate, LocalDate endDate) {
        
		if (endDate.compareTo(startDate) < 0) {
			
            throw new IllegalArgumentException("The date range is invalid.");
        }
        
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    public LocalDate getStartDate() {
		
    	return this.startDate;
	}
	
    void setStartDate(LocalDate startDate) {
		
    	this.startDate = startDate;
	}
	
    public LocalDate getEndDate() {
		
    	return this.endDate;
	}
    
	void setEndDate(LocalDate endDate) {
		
		this.endDate = endDate;
	}
}
