/**
 *
 */
package info.quantlab.tutorium.solution03;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

/**
 * A simple class that provides a function to calculate the quotient of a whole number divided by
 * the sum of all numbers up to the given one. I.e. N / average(1,...,N) .
 *
 * @author Roland Bachl
 *
 */
public class SequenceNDivByAverage {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int nmax = 100000;

		ForkJoinPool.commonPool().submit(() ->	// You can disregard this. This is just to make the
		Stream.of(0).forEach(x ->				// stack trace a bit more interesting.
		{
			for (int n = 0; n < nmax; n++) {
				System.out.format("n = %4d:\t%1.8f\n", n , getQuotient(n));
			}
		}
				)).get();								// Same as above.
	}

	/**
	 * @param n
	 * @return n / average(1,...,n)
	 */
	public static double getQuotient(int n) {
		return n / getAverage(n);
	}

	private static double getAverage(int n) {
		long sum = 0;
		for(int m = 1; m < n+1; m++) {
			sum += m;
		}
		return (double) sum / n;
	}

}