package info.quantlab.tutorium.session03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

class SequenceNDivByAverageTest {

	private static final int numberOfTests	= 10;
	private static final double tolerance	= 1e-7;

	@Test
	void test() {
		Random rng = new Random();
		for(int i = 0; i < numberOfTests; i++) {
			int n = rng.nextInt(Integer.MAX_VALUE-1)+1;
			assertEquals(getQuotient(n), SequenceNDivByAverage.getQuotient(n), tolerance);
		}
	}

	public static double getQuotient(int n) {
		return n / getAverageSmart(n);
	}

	private static double getAverageSmart(int n) {
		return (1+n)*0.5;
	}
}
