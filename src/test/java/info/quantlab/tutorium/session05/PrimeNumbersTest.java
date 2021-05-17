/**
 *
 */
package info.quantlab.tutorium.session05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

/**
 * @author Roland Bachl
 *
 */
public class PrimeNumbersTest {

	PrimeNumbers testPrimes = info.quantlab.tutorium.session05.PrimeNumbersParallel.getInstance();
	PrimeNumbers checkPrimes = info.quantlab.tutorium.solution05.PrimeNumbersParallel.getInstance();

	static InputStream stdIn = System.in;

	@Test
	public void testPrimeRange() {
		long minNumber = Integer.MAX_VALUE - 150;
		long maxNumber = Integer.MAX_VALUE + 150;

		//Sort the lists, just in case
		List<Long> testList = testPrimes.getPrimes(minNumber, maxNumber)
				.stream().sorted().collect(Collectors.toList());
		List<Long> checkList = checkPrimes.getPrimes(minNumber, maxNumber)
				.stream().sorted().collect(Collectors.toList());

		assertEquals(checkList.size(), testList.size());
		for(int i = 0; i < checkList.size(); i++) {
			assertEquals(checkList.get(i), testList.get(i));
		}
	}

	@Test
	public void testPrimeManual() {
		//Override System.in to fake user input.
		System.setIn(new InputStream() {

			@Override
			public int read() throws IOException {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {	}
				return 10;
			}
		});

		List<Long> testList = testPrimes.getPrimes();

		assertTrue(testList.size() > 10);
		for (long prime : testList) {
			assertTrue(info.quantlab.tutorium.solution05.PrimeNumbersParallel.isPrime(prime));
		}
	}

	@AfterAll
	public static void resetSystemIn() {
		System.setIn(stdIn);
	}
}
