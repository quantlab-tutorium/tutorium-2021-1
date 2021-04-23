package info.quantlab.tutorium.session02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FibonacciTest {

	private static int[] limitsToTest = {1, 10, 100, 10000};

	@Test
	void test() {
		for(int limit : limitsToTest) {
			int actual 		= Fibonacci.sumEven(limit);
			int expected 	= info.quantlab.tutorium.solution02.Fibonacci.sumEven(limit);

			assertEquals(expected, actual);
		}

	}

}
