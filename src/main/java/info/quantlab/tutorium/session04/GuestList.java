package info.quantlab.tutorium.session04;

import java.util.Collection;
import java.util.Set;

/**
 * Organize guests in hotel rooms.
 *
 * @author Roland Bachl
 *
 */
public interface GuestList {

	/**
	 * Get the name of the guest in the given hotel room.
	 *
	 * @param roomNumber
	 * @return The guest name.
	 */
	String getGuestName(int roomNumber);

	/**
	 * Get the names of all guests.
	 *
	 * @return A collection view of all guests.
	 */
	Collection<String> getGuests();

	/**
	 * All currently occupied rooms. As a set of unique entries.
	 *
	 * @return A set view of all occupied rooms.
	 */
	Set<Integer> getOccupiedRooms();

	/**
	 * Checkout guest in the given room. (I.e. remove them)
	 * @param roomNumber
	 */
	void checkout(int roomNumber);

	/**
	 * Checkin a new guest in the given room.
	 *
	 * @param roomNumber
	 * @param guestName
	 * @throws IllegalArgumentException Thrown when the specified room is already occupied.
	 */
	void checkin(int roomNumber, String guestName) throws IllegalArgumentException;
}
