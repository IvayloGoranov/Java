package hotelBookingSystem.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import hotelBookingSystem.interfaces.IDbEntity;
import hotelBookingSystem.interfaces.IUser;

public class Booking implements IDbEntity {

	private BigDecimal totalPrice;
	private int id;
	private RegularUser client;
	private String comments;
	private LocalDate startBookDate;
	private LocalDate endBookDate;
	
    public Booking(IUser client, LocalDate startBookDate, LocalDate endBookDate, BigDecimal totalPrice, String comments) {
    	
        if (endBookDate.compareTo(startBookDate) < 0)
        {
            throw new IllegalArgumentException("The date range is invalid.");
        }

        this.startBookDate = startBookDate;
        this.endBookDate = endBookDate;
        this.setTotalPrice(totalPrice);
        this.comments = comments;
    }

	@Override
	public int getId() {
		
		return this.id;
	}
	
	@Override
	public void setId(int id) {
		
		this.id = id;
	}

    public String getComments() {
		
    	return this.comments;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	private void setTotalPrice(BigDecimal value) {
		
		if (value.compareTo(BigDecimal.ZERO) < 0) {
			
            throw new IllegalArgumentException("The total price must not be less than 0.");
        }

        this.totalPrice = value;
	}
	
	public RegularUser getClient() {
		
		return this.client;
	}

	public LocalDate getStartBookDate() {
		
		return this.startBookDate;
	}

	public LocalDate getEndBookDate() {
		
		return this.endBookDate;
	}
}
