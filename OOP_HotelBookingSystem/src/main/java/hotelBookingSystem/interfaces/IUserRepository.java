package hotelBookingSystem.interfaces;

public interface IUserRepository extends IRepository<IUser> {

	IUser getByUsername(String username);
}
