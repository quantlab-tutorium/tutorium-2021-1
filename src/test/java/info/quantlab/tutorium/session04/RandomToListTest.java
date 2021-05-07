/**
 *
 */
package info.quantlab.tutorium.session04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author Roland Bachl
 *
 */
public class RandomToListTest {
	private static long seed	= 1337L;
	private static int size		= 10000;

	@Test
	public void testRandomList() {
		List<Double> testList = info.quantlab.tutorium.session04.RandomToList.getList(seed, size);
		List<Double> checkList = info.quantlab.tutorium.solution04.RandomToList.getList(seed, size);

		for(int i = 0; i < size; i++) {
			assertEquals(checkList.get(i), testList.get(i));
		}
	}

}
