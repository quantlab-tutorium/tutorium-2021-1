/**
 *
 */
package info.quantlab.tutorium.solution04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import info.quantlab.tutorium.session04.GuestList;

/**
 * A {@link GuestList} based on {@link Map}.
 *
 * @author Roland Bachl
 *
 */
public class GuestListFromMap implements GuestList {

	/**
	 * Store all guests in a map.
	 */
	private final Map<Integer, String> guestlist = new HashMap<Integer, String>();

	@Override
	public String getGuestName(int roomNumber) {
		return guestlist.get(roomNumber);
	}

	@Override
	public Collection<String> getGuests() {
		return Collections.unmodifiableCollection(guestlist.values());
	}

	@Override
	public Set<Integer> getOccupiedRooms() {
		return Collections.unmodifiableSet(guestlist.keySet());
	}

	@Override
	public void checkout(int roomNumber) {
		guestlist.remove(roomNumber);
	}

	@Override
	public void checkin(int roomNumber, String guestName) {
		if(guestlist.containsKey(roomNumber)) {
			throw new IllegalArgumentException("Cannot checkin to room " + roomNumber +
					". The room is already occupied.");
		}
		guestlist.put(roomNumber, guestName);
	}

	/**
	 * Import a guest list via CSV file.
	 *
	 * @param csv The file.
	 * @return The guest list
	 * @throws IOException
	 */
	public static GuestList getListFromCSV(File csv) throws IOException {

		GuestListFromMap gl = new GuestListFromMap();

		BufferedReader csvReader = new BufferedReader(new FileReader(csv));
		String row;
		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(",");
			gl.guestlist.put(Integer.parseInt(data[0]), data[1]);
		}
		csvReader.close();

		return gl;
	}

}
