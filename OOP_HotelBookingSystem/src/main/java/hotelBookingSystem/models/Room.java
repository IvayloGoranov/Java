package hotelBookingSystem.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import hotelBookingSystem.interfaces.IDbEntity;

public class Room implements IDbEntity {

	private int placesCount;
    private BigDecimal pricePerDay;
    private int id;
    private Collection<Booking> bookings;
    private Collection<AvailableDate> availableDates;

    public Room(int placesCount, BigDecimal pricePerDay) {
    	
        this.setPlacesCount(placesCount);
        this.setPricePerDay(pricePerDay);
        this.bookings = new ArrayList<Booking>();
        this.availableDates = new ArrayList<AvailableDate>();
    }

    @Override
	public int getId() {

    	return this.id;
	}

	@Override
	public void setId(int id) {

		this.id = id;
	}

    public int getPlacesCount() {
		
    	return this.placesCount;
	}

    private void setPlacesCount(int value) {
    	
    	if (value < 0) {
    		
            throw new IllegalArgumentException("The places must not be less than 0.");
        }

        this.placesCount = value;
    }


    public BigDecimal getPricePerDay() {
		
    	return this.pricePerDay;
	}

    public void setPricePerDay(BigDecimal value) {
		
    	if (value.compareTo(BigDecimal.ZERO) < 0) {
			
            throw new IllegalArgumentException("The total price must not be less than 0.");
        }

        this.pricePerDay = value;
	}

	public Collection<Booking> getBookings() {
		
		return this.bookings;
	}

	public Collection<AvailableDate> getAvailableDates() {
		
		return this.availableDates;
	}
}
