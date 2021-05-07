/**
 *
 */
package info.quantlab.tutorium.session04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import info.quantlab.tutorium.solution04.GuestListFromMap;

/**
 * @author Roland Bachl
 *
 */
public class GuestListTest {

	private static File file = new File("src/test/resources/info/quantlab/tutorium/session04", "guestlist.csv");

	@Test
	public void testGuestList() throws IOException {

		GuestList testList	= GuestListImplementation.getListFromCSV(file);
		GuestList checkList	= GuestListFromMap.getListFromCSV(file);

		for(int room : checkList.getOccupiedRooms()) {
			assertEquals(checkList.getGuestName(room), testList.getGuestName(room));
		}
	}
}
